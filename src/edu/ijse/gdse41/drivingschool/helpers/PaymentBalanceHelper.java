/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.helpers;

import edu.ijse.gdse41.drivingschool.dbcontroller.CustAmountDBController;
import edu.ijse.gdse41.drivingschool.dbcontroller.PaymentDBController;
import edu.ijse.gdse41.drivingschooldto.PaymentDTO;
import java.sql.SQLException;

/**
 *
 * @author Sandman
 */
public class PaymentBalanceHelper {
    public static boolean runBalanceUpdater(PaymentDTO payDto) throws SQLException, ClassNotFoundException{

        if(PaymentDBController.addPayment(payDto)){
            double balance=CustAmountDBController.getCustBalance(payDto.getAddmissionId());
            System.out.println(balance);
            double currentPay=PaymentDBController.getPaymentsSum(payDto.getAddmissionId());
            System.out.println(currentPay);
            double newBalance=balance-payDto.getAmount();
            System.out.println(newBalance);
            if(CustAmountDBController.balanceUpdater(payDto, newBalance)){
                return true;
            }
            
        }
        return false;
   } 
}
