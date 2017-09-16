package com.ikempf.sandbox.sam_subtyping_bug

import com.ikempf.sandbox.sam_subtyping_bug.JsonValue.{JsonNumber, JsonObject, JsonString}

trait JsonEncoder[A] {
  def encode(value: A): JsonValue
}

trait JsonObjectEncoder[A] extends JsonEncoder[A] {
  def encode(value: A): JsonObject
}

object JsonEncoderInstances {

  implicit val stringEncoder: JsonEncoder[String] =
    JsonString.apply

  implicit val intEncoder: JsonEncoder[Int] =
    i => JsonNumber(i)

  implicit def listEncoder[A](implicit encoder: JsonEncoder[A]): JsonObjectEncoder[List[A]] =
    l => JsonObject(l.zipWithIndex.map {
      case (a, index) => index.toString -> encoder.encode(a)
    })

  implicit def tuple2Encoder[A, B](implicit encoderA: JsonEncoder[A], encoderB: JsonEncoder[B]): JsonObjectEncoder[(A, B)] = {
    case (a, b) => JsonObject(List(
      "a" -> encoderA.encode(a),
      "b" -> encoderB.encode(b))
    )
  }

}