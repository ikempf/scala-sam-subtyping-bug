package com.ikempf.sam_subtyping_bug

import JsonEncoderInstances._
import org.scalatest.{FlatSpec, Matchers}

class JsonEncoderInstancesTest extends FlatSpec with Matchers {

  "ProductEncoder" should "derive JsonEncoder typeclasses" in {
    implicitly[JsonEncoder[List[String]]].encode(List("a","b"))
    //implicitly[JsonEncoder[(String, Int)]].encode(("str", 2))
  }

}