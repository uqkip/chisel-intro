/*
 * This code is a minimal hardware described in Chisel.
 * 
 * Blinking LED: the FPGA version of Hello World
 */

import chisel3._
import chisel3.Driver

/**
 * The blinking LED component.
 */

class Hello extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(1.W))
  })
  val CNT_MAX = (50000000 / 4 - 1).U;

  val cntReg = RegInit(0.U(32.W))
  val blkReg = RegInit(0.U(1.W))

  cntReg := cntReg + 1.U
  when(cntReg === CNT_MAX) {
    blkReg := 1.U(1.W)
  }
  when(cntReg === CNT_MAX*4.U) {
    cntReg := 0.U
    blkReg := 0.U(1.W)
  }
  io.led := blkReg
}

/**
 * An object extending App to generate the Verilog code.
 */
object Hello extends App {
  chisel3.Driver.execute(Array[String](), () => new Hello())
}
