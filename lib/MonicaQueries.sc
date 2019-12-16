import $file.MonicaModel

import MonicaModel._

def getRelationshipTypesPage(page: Option[Int] = None): RelationshipTypesPage = {
  val path = page match {
    case None => "/relationshiptypes"
    case Some(pageIndex) => s"/relationshiptypes?page=${pageIndex}"
  }
  val response = requests.get(baseURL + path,
                              headers = Map(authHeader))
  JSONPickler.read[RelationshipTypesPage](response.text)
}

def relationshipTypes(): Stream[RelationshipType] = {
  val first = getRelationshipTypesPage()

  def go(current: RelationshipTypesPage): Stream[RelationshipType] =
    if (current.meta.current_page == current.meta.last_page) {
      current.data.toStream
    } else {
      val next = getRelationshipTypesPage(Some(current.meta.current_page + 1))
      current.data.toStream #::: go(next)
    }

  go(first)
}
