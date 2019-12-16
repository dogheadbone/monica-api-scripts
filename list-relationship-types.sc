#!/usr/bin/env -S amm --predef ./predef.sc

import $file.lib.MonicaQueries

@main
def main() {
  println("listing all relationship types")
  val relationshipTypes = MonicaQueries.relationshipTypes()
  for(relationshipType <- relationshipTypes) {
    println(s"relationship name: ${relationshipType.name}")
    println(s"has id: ${relationshipType.id}")
  }
}
