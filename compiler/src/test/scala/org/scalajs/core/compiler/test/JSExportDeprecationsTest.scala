package org.scalajs.core.compiler.test

import org.scalajs.core.compiler.test.util._
import org.junit.Test

// scalastyle:off line.size.limit

class JSExportDeprecationsTest extends DirectTest with TestHelpers {

  override def extraArgs: List[String] =
    super.extraArgs :+ "-deprecation"

  override def preamble: String =
    """import scala.scalajs.js, js.annotation._
    """

  @Test
  def warnJSExportClass: Unit = {
    """
    @JSExport
    class A

    @JSExport("Foo")
    class B
    """ hasWarns
    """
      |newSource1.scala:3: warning: @JSExport on classes is deprecated and will be removed in 1.0.0. Use @JSExportTopLevel instead (which does exactly the same thing on classes).
      |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
      |    @JSExport
      |     ^
      |newSource1.scala:6: warning: @JSExport on classes is deprecated and will be removed in 1.0.0. Use @JSExportTopLevel instead (which does exactly the same thing on classes).
      |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
      |    @JSExport("Foo")
      |     ^
    """
  }

  @Test
  def warnJSExportObject: Unit = {
    """
    @JSExport
    object A

    @JSExport("Foo")
    object B
    """ hasWarns
    """
      |newSource1.scala:3: warning: @JSExport on objects is deprecated and will be removed in 1.0.0. Use @JSExportTopLevel instead. Note that it exports the object itself (rather than a 0-arg function returning the object), so the calling JavaScript code must be adapted.
      |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
      |    @JSExport
      |     ^
      |newSource1.scala:6: warning: @JSExport on objects is deprecated and will be removed in 1.0.0. Use @JSExportTopLevel instead. Note that it exports the object itself (rather than a 0-arg function returning the object), so the calling JavaScript code must be adapted.
      |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
      |    @JSExport("Foo")
      |     ^
    """
  }

  @Test
  def warnJSExportDescendentClasses: Unit = {
    for (kind <- Seq("class", "trait", "object")) {
      s"""
      @JSExportDescendentClasses
      $kind A

      @JSExportDescendentClasses(ignoreInvalidDescendants = true)
      $kind B
      """ hasWarns
      """
        |newSource1.scala:3: warning: @JSExportDescendentClasses is deprecated and will be removed in 1.0.0. For use cases where you want to simulate "reflective" instantiation, use @EnableReflectiveInstantion and scala.scalajs.reflect.Reflect.lookupInstantiatableClass instead.
        |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
        |      @JSExportDescendentClasses
        |       ^
        |newSource1.scala:6: warning: @JSExportDescendentClasses is deprecated and will be removed in 1.0.0. For use cases where you want to simulate "reflective" instantiation, use @EnableReflectiveInstantion and scala.scalajs.reflect.Reflect.lookupInstantiatableClass instead.
        |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
        |      @JSExportDescendentClasses(ignoreInvalidDescendants = true)
        |       ^
      """
    }
  }

  @Test
  def warnJSExportDescendentObjects: Unit = {
    for (kind <- Seq("class", "trait", "object")) {
      s"""
      @JSExportDescendentObjects
      $kind A

      @JSExportDescendentObjects(ignoreInvalidDescendants = true)
      $kind B
      """ hasWarns
      """
        |newSource1.scala:3: warning: @JSExportDescendentObjects is deprecated and will be removed in 1.0.0. For use cases where you want to simulate "reflective" loading, use @EnableReflectiveInstantion and scala.scalajs.reflect.Reflect.lookupLoadableModuleClass instead.
        |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
        |      @JSExportDescendentObjects
        |       ^
        |newSource1.scala:6: warning: @JSExportDescendentObjects is deprecated and will be removed in 1.0.0. For use cases where you want to simulate "reflective" loading, use @EnableReflectiveInstantion and scala.scalajs.reflect.Reflect.lookupLoadableModuleClass instead.
        |  (you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
        |      @JSExportDescendentObjects(ignoreInvalidDescendants = true)
        |       ^
      """
    }
  }

}
