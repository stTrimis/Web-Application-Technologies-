package calculator;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "CalculatorMB")
@RequestScoped
public class CalculatorMB {

    private double operand1, operand2, result;

    public CalculatorMB() {
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public double getResult() {
        return result;
    }

    public String calculate() {
        result = operand1 + operand2;
        return "page2";
    }

}
