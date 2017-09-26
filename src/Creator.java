import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Creator {

  public static void main(String args[]) {
    Creator creator = new Creator();
    creator.createWorkOrders();
  }

  private void createWorkOrders() {
    // read input, create work orders and write as json files
    ObjectMapper mapper = new ObjectMapper();
    Scanner systemInScanner = new Scanner(System.in);

    int orderId = parseInt(getOrderId(systemInScanner));
    String orderDescription = getOrderDescription(systemInScanner);
    String orderSubmitter = getOrderSubmitter(systemInScanner);

    WorkOrder workOrder = new WorkOrder();
    workOrder.setId(orderId);
    workOrder.setDescription(orderDescription);
    workOrder.setSenderName(orderSubmitter);
    workOrder.setStatus(Status.values()[0]);

    try {
      System.out.println(mapper.writeValueAsString(workOrder));
      File file = new File("./src/" + workOrder.getId() + ".json");
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(mapper.writeValueAsString(workOrder));
      fileWriter.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    System.out.println("Would you like to enter another work order? ");
    System.out.print("y or n: ");
    if (systemInScanner.next().equals("y")) {
      createWorkOrders();
    }
  }

  private String getOrderId(Scanner systemInScanner) {
    System.out.print("Please provide an ID for the work order: ");
    String orderId = systemInScanner.nextLine();
    if (orderId.trim().isEmpty()) {
      System.out.println("ID cannot be blank.");
      getOrderId(systemInScanner);
    } else {
      return orderId;
    }
    return null;
  }

   private String getOrderDescription(Scanner systemInScanner) {
     System.out.print("Please provide a description of the work order: ");
     String orderDescription = systemInScanner.nextLine();
     if (orderDescription.trim().isEmpty()) {
       System.out.println("Description cannot be blank.");
       getOrderDescription(systemInScanner);
     } else {
       return orderDescription;
     }
     return null;
   }

   private String getOrderSubmitter(Scanner systemInScanner) {
     System.out.print("Please provide the name of the person who submitted the order: ");
     String orderSubmitter = systemInScanner.nextLine();
     if (orderSubmitter.trim().isEmpty()) {
       System.out.println("Submitter cannot be blank.");
       getOrderSubmitter(systemInScanner);
     } else {
       return orderSubmitter;
     }
     return null;
   }
}