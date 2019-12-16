final case class RelationshipType(
  name: String,
  id: Int
)

final object RelationshipType {
  implicit val rw: JSONRW[RelationshipType] = autoJSONRW
}

final case class PageMeta(
  current_page: Int,
  last_page: Int,
  total: Int
)

final object PageMeta {
  implicit val rw: JSONRW[PageMeta] = autoJSONRW
}

final case class RelationshipTypesPage(
  data: List[RelationshipType],
  meta: PageMeta
)

final object RelationshipTypesPage {
  implicit val rw: JSONRW[RelationshipTypesPage] = autoJSONRW
}
