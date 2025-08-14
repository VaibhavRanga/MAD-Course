package com.hadiyarajesh.week10.day2_design_patterns

interface PaymentProcessor {
    fun processUPIPayment(amount: Int)
    fun processCreditCardPayment(amount: Int)
}

class UPIPaymentProcessor : PaymentProcessor {
    override fun processUPIPayment(amount: Int) {
        println("Processing UPI payment of $amount")
    }

    override fun processCreditCardPayment(amount: Int) {
        throw UnsupportedOperationException("UPI payment processor does not support credit card payments")
    }
}

class CreditCardPaymentProcessor : PaymentProcessor {
    override fun processUPIPayment(amount: Int) {
        throw UnsupportedOperationException("Credit card payment processor does not support UPI payments")
    }

    override fun processCreditCardPayment(amount: Int) {
        println("Processing credit card payment of $amount")
    }
}

enum class PaymentType {
    UPI,
    CREDIT_CARD
}

interface UnifiedPaymentProcessor {
    fun processPayment(paymentType: PaymentType, amount: Int)
}

class UnifiedPaymentProcessorImpl : UnifiedPaymentProcessor {
    override fun processPayment(
        paymentType: PaymentType,
        amount: Int
    ) {
        val adapter = PaymentAdapter(paymentType)
        adapter.processPayment(paymentType, amount)
    }

}

class PaymentAdapter(paymentType: PaymentType) : UnifiedPaymentProcessor {
    val paymentProcessor: PaymentProcessor = when (paymentType) {
        PaymentType.UPI -> UPIPaymentProcessor()
        PaymentType.CREDIT_CARD -> CreditCardPaymentProcessor()
    }

    override fun processPayment(
        paymentType: PaymentType,
        amount: Int
    ) {
        when (paymentType) {
            PaymentType.UPI -> paymentProcessor.processUPIPayment(amount)
            PaymentType.CREDIT_CARD -> paymentProcessor.processCreditCardPayment(amount)
        }
    }
}

fun main() {
//    val upiPaymentProcessor: PaymentProcessor = UPIPaymentProcessor()
//    val creditCardPaymentProcessor: PaymentProcessor = CreditCardPaymentProcessor()
//
//    upiPaymentProcessor.processUPIPayment(100)
//    creditCardPaymentProcessor.processCreditCardPayment(200)

    val unifiedPaymentProcessor = UnifiedPaymentProcessorImpl()
    unifiedPaymentProcessor.processPayment(PaymentType.UPI, 100)
    unifiedPaymentProcessor.processPayment(PaymentType.CREDIT_CARD, 200)
}
