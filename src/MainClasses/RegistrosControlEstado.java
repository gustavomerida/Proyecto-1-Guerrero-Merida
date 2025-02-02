/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class RegistrosControlEstado {
    private int psw;
    private int pc;
    private int MAR;
    
    public RegistrosControlEstado(int psw, int pc, int MAR){
        this.psw = psw;
        this.pc = pc;
        this.MAR = MAR;
    }

    public int getPsw() {
        return psw;
    }

    public int getPc() {
        return pc;
    }

    public int getMAR() {
        return MAR;
    }
    
    
         
}
