package stat.jupiter;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.platform.commons.support.AnnotationSupport;
import stat.model.EmployeeDTO;

import static helpers.utils.sql.SqlHelper.deleteQuery;
import static helpers.utils.sql.SqlHelper.insertQuery;

public class EmployeeExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, ParameterResolver {
  public static final Namespace NAMESPACE = Namespace.create(EmployeeExtension.class);

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Employee.class)
        .ifPresent(anno -> {
          EmployeeDTO e = EmployeeDTO.fromAnno(anno);
          long employeeId = insertQuery("""
              INSERT INTO seller (fsfirst_name, fssecond_name, fsmiddle_name, fsemail, fiseller_type_id, fiseller_group_id)
              VALUES (?, ?, ?, ?, ?, ?)
              """, e.firstName(), e.lastName(), e.surname(), e.email(), e.typeId(), e.groupId());

          context.getStore(NAMESPACE).put(
              context.getRequiredTestMethod().getName() + "_" + context.getUniqueId(),
              e.withId(employeeId)
          );
        });
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    EmployeeDTO e = context.getStore(NAMESPACE).get(
        context.getRequiredTestMethod().getName() + "_" + context.getUniqueId(),
        EmployeeDTO.class
    );

    deleteQuery("""
        DELETE FROM seller WHERE fiseller_id = ?
        """, e.id());
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().isAssignableFrom(EmployeeDTO.class);
  }

  @Override
  public EmployeeDTO resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return extensionContext.getStore(NAMESPACE).get(
        extensionContext.getRequiredTestMethod().getName() + "_" + extensionContext.getUniqueId(),
        EmployeeDTO.class
    );
  }
}
