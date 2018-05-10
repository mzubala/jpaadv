package pl.com.bottega.qma.hr;

import java.util.Set;

public interface HrFacade {
  Set<Long> getEmployeesInDepartments(Set<String> departmentCodes);
}
