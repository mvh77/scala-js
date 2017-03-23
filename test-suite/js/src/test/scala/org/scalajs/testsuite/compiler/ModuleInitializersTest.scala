/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js Test Suite        **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013-2017, LAMP/EPFL   **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */
package org.scalajs.testsuite.compiler

import org.junit.Test
import org.junit.Assert._

class ModuleInitializersTest {
  import ModuleInitializersTest._

  @Test def correctInitializers(): Unit = {
    assertArrayEquals(
        Array[AnyRef](NoConfigMain, TestConfigMain2, TestConfigMain1),
        moduleInitializersEffects.toArray[AnyRef])
  }

}

object ModuleInitializersTest {
  final val NoConfigMain = "ModuleInitializerInNoConfiguration.main"
  final val CompileConfigMain = "ModuleInitializerInCompileConfiguration.main"
  final val TestConfigMain1 = "ModuleInitializerInTestConfiguration.main1"
  final val TestConfigMain2 = "ModuleInitializerInTestConfiguration.main2"

  val moduleInitializersEffects =
    new scala.collection.mutable.ListBuffer[String]
}

object ModuleInitializerInNoConfiguration {
  import ModuleInitializersTest._

  def main(): Unit = {
    moduleInitializersEffects += NoConfigMain
  }
}

object ModuleInitializerInCompileConfiguration {
  import ModuleInitializersTest._

  def main(): Unit = {
    // This is not going to be actually run
    moduleInitializersEffects += CompileConfigMain
  }
}

object ModuleInitializerInTestConfiguration {
  import ModuleInitializersTest._

  def main1(): Unit = {
    moduleInitializersEffects += TestConfigMain1
  }

  def main2(): Unit = {
    moduleInitializersEffects += TestConfigMain2
  }
}
