package pl.com.bottega.qma.hr;

import java.util.*;
import java.util.stream.Collectors;

public class HardcodedHrFacade implements HrFacade {

  private final Map<String, Set<Long>> employees = new HashMap<>();

  public HardcodedHrFacade() {
    employees.put("D1", new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L)));
    employees.put("D2", new HashSet<>(Arrays.asList(1L, 2L, 3L, 10L, 15L)));
  }

  @Override
  public Set<Long> getEmployeesInDepartments(Set<String> departmentCodes) {
    return employees.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
  }
}
