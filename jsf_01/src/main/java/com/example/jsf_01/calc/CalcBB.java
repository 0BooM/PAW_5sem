package com.example.jsf_01.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {

    private String x;
    private String okres;
    private String procent;
    private Double result;

    @Inject
    FacesContext ctx;

    public String getOkres() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres = okres;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getProcent() {
        return procent;
    }

    public void setProcent(String procent) {
        this.procent = procent;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    // Główna metoda obliczeń
    public String calc() {
        try {
            double x = Double.parseDouble(this.x);
            double okres = Double.parseDouble(this.okres);
            double procent = Double.parseDouble(this.procent) / 100.0; // zamiana % na ułamek

            double odsetki = x * (procent / 12) * okres;
            result = x + odsetki;

            // zaokrąglenie do dwóch miejsc po przecinku
            result = Math.round(result * 100.0) / 100.0;
            odsetki = Math.round(odsetki * 100.0) / 100.0;

            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Operacja wykonana poprawnie. Odsetki: " + odsetki + " zł, Kwota do spłaty: " + result + " zł",
                    null
            ));

            return "showresult";

        } catch (Exception e) {
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania danych", null));
            return null;
        }
    }

    public String info() {
        return "info";
    }
}
