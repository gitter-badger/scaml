package org

/**
 * Scex is tool to build documents in Scala.
 */
package object scex {
  type Element = Node.Element
  val  Element = Node.Element
  
  type Attribute[T] = Annotation.Attribute[T]
  type Processor[T] = Annotation.Processor[T]
  
  type Builder = Node.Builder
  
  type Text = Node.Text
  val  Text = Node.Text
  
  implicit def strToText(str: String) = Text(str)
  
  implicit def strsToTexts(strs: Seq[String]) = strs.map(Text(_))
}
