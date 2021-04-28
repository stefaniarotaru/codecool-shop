function paymentOptionDisplay() {
    let selection = document.getElementById("paymentOptions").value;
    if (selection == "card") {document.getElementById("cardPayment").style.display = "block",
        document.getElementById("paypalPayment").style.display = "none"}

    else if (selection == "paypal") {document.getElementById("paypalPayment").style.display = "block",
        document.getElementById("cardPayment").style.display = "none"}
}

var select = document.getElementById("month");
for(var i=1; i<=12; i++){

    var option = document.createElement("OPTION");
    select.options.add(option);
    option.text = i;
    option.value = i;
}
