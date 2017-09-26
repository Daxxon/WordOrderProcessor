import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;

public class Processor {
  Map<Status, Set> workOrderMap = new HashMap<>();
  Set<WorkOrder> initial = new HashSet<>();
  Set<WorkOrder> assigned = new HashSet<>();
  Set<WorkOrder> inProgress = new HashSet<>();
  Set<WorkOrder> done = new HashSet<>();


  public void processWorkOrders() {
    workOrderMap.put(Status.INITIAL, initial);
    workOrderMap.put(Status.ASSIGNED, assigned);
    workOrderMap.put(Status.IN_PROGRESS, inProgress);
    workOrderMap.put(Status.DONE, done);

    for (Map.Entry<Status, java.util.Set> entry : workOrderMap.entrySet()) {
      System.out.println("Key : " + entry.getKey() + "\n" + "Value : " + entry.getValue());
    }

    moveIt();
    readIt();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
    processWorkOrders();
  }

  private void moveIt() {
    for (WorkOrder order : inProgress) {
      order.setStatus(Status.DONE);
    }

    for (WorkOrder order : inProgress) {
      done.add(order);
    }

    inProgress.clear();

    for (WorkOrder order : assigned) {
      order.setStatus(Status.IN_PROGRESS);
    }

    for (WorkOrder order : assigned) {
      inProgress.add(order);
    }

    assigned.clear();

    for (WorkOrder order : initial) {
      order.setStatus(Status.ASSIGNED);
    }

    for (WorkOrder order : initial) {
      assigned.add(order);
    }

    initial.clear();
  }

  private void readIt() {
    ObjectMapper mapper = new ObjectMapper();
    File currentDirectory = new File("src");
    File files[] = currentDirectory.listFiles();
    try {
      for (File order : files) {
        if (order.getName().endsWith(".json")) {
          WorkOrder workOrder = mapper.readValue(order, WorkOrder.class);
          initial.add(workOrder);
        }
      }
      for (File order : files) {
        if(order.getName().endsWith(".json")) {
          order.delete();
        }
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static void main(String args[]) {
    Processor processor = new Processor();
    processor.processWorkOrders();
  }
}