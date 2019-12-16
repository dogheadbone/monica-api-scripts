import ammonite.ops._
import collection.mutable
import sys.process._

val baseURL = read(pwd/RelPath("../dogheadbone-secrets/monica-demo-base-url.txt")).trim
val bearerToken =
  read(pwd/RelPath("../dogheadbone-secrets/monica-demo-personal-access-token-0.key")).trim
val authHeader = "Authorization" -> s"Bearer ${bearerToken}"

final object JSONPickler extends upickle.AttributeTagged {
  override implicit def OptionWriter[T: Writer]: Writer[Option[T]] =
    implicitly[Writer[T]].comap[Option[T]] {
      case None => null.asInstanceOf[T]
      case Some(x) => x
    }

  override implicit def OptionReader[T: Reader]: Reader[Option[T]] = {
    new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))){
      override def visitNull(index: Int) = None
    }
  }
}

import JSONPickler.{ReadWriter => JSONRW, macroRW => autoJSONRW}
