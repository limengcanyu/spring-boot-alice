package com.vue.element.admin.controller;

import com.vue.element.admin.dto.Result;
import com.vue.element.admin.dto.CompanyUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/29 22:03
 */
@Slf4j
@RestController
public class UserController {

    @PostMapping("/vue-element-admin/user/login")
    public Result login(@RequestBody CompanyUserDto companyUserDto) {
        log.debug("====== login user: {}", companyUserDto);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", companyUserDto.getUsername() + "-token");

        return new Result(20000, null, tokenMap);
    }

    @GetMapping("/vue-element-admin/user/info")
    public Result info(@RequestParam String token) {
        log.debug("====== info token: {}", token);

        CompanyUserDto companyUserDto = new CompanyUserDto();
        companyUserDto.setName("Super Admin");
        companyUserDto.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        companyUserDto.setIntroduction("I am a super administrator");
        companyUserDto.setRoles(Collections.singletonList("admin"));
        companyUserDto.setRoutePaths(getRoutePaths());
        companyUserDto.setRouteNames(getRouteNames());
        return new Result(20000, null, companyUserDto);
    }

    @PostMapping("/vue-element-admin/user/logout")
    public Result logout() {
        log.debug("====== logout");

        return new Result(20000, null, null);
    }

    private List<String> getRoutes(List<String> permissionRoutes, List<String> iconRoutes, List<String> componentsRoutes,
                                   List<String> chartsRoutes, List<String> nestedRoutes, List<String> tableRoutes,
                                   List<String> exampleRoutes, List<String> tabRoutes, List<String> errorRoutes,
                                   List<String> errorLogRoutes, List<String> excelRoutes, List<String> zipRoutes,
                                   List<String> pdfRoutes, List<String> themeRoutes, List<String> clipboardRoutes,
                                   List<String> externalLinkRoutes, List<String> employeeRoutes, List<String> salaryRoutes) {
        List<String> routePaths = new ArrayList<>();
        routePaths.addAll(permissionRoutes);
        routePaths.addAll(iconRoutes);
        routePaths.addAll(componentsRoutes);
        routePaths.addAll(chartsRoutes);
        routePaths.addAll(nestedRoutes);
        routePaths.addAll(tableRoutes);
        routePaths.addAll(exampleRoutes);
        routePaths.addAll(tabRoutes);
        routePaths.addAll(errorRoutes);
        routePaths.addAll(errorLogRoutes);
        routePaths.addAll(excelRoutes);
        routePaths.addAll(zipRoutes);
        routePaths.addAll(pdfRoutes);
        routePaths.addAll(themeRoutes);
        routePaths.addAll(clipboardRoutes);
        routePaths.addAll(externalLinkRoutes);
        routePaths.addAll(employeeRoutes);
        routePaths.addAll(salaryRoutes);

        return routePaths;
    }

    private List<String> getRoutePaths(){
        List<String> permissionRoutes = Arrays.asList("/permission", "page", "directive", "role");
        List<String> iconRoutes = Arrays.asList("/icon", "index");
        List<String> componentsRoutes = Arrays.asList("/components", "tinymce", "markdown", "json-editor", "split-pane",
                "avatar-upload", "dropzone", "sticky", "count-to", "mixin", "back-to-top", "drag-dialog", "drag-select",
                "dnd-list", "drag-kanban");
        List<String> chartsRoutes = Arrays.asList("/charts", "keyboard", "line", "mix-chart");
        List<String> nestedRoutes = Arrays.asList("/nested", "menu1", "menu1-1", "menu1-2", "menu1-2-1", "menu1-2-2", "menu1-3", "menu2");
        List<String> tableRoutes = Arrays.asList("/table", "dynamic-table", "drag-table", "inline-edit-table", "complex-table");
        List<String> exampleRoutes = Arrays.asList("/example", "create", "edit", "list");
        List<String> tabRoutes = Arrays.asList("/tab", "index");
        List<String> errorRoutes = Arrays.asList("/error", "401", "404");
        List<String> errorLogRoutes = Arrays.asList("/error-log", "log");
        List<String> excelRoutes = Arrays.asList("/excel", "export-excel", "export-selected-excel", "export-merge-header", "upload-excel");
        List<String> zipRoutes = Arrays.asList("/zip", "download");
        List<String> pdfRoutes = Arrays.asList("/pdf", "index", "/pdf/download");
        List<String> themeRoutes = Arrays.asList("/theme", "index");
        List<String> clipboardRoutes = Arrays.asList("/clipboard", "index");
        List<String> externalLinkRoutes = Arrays.asList("/external-link", "https://github.com/PanJiaChen/vue-element-admin");
        List<String> employeeRoutes = Arrays.asList("/employee", "employee-list", "employee-add");
        List<String> salaryRoutes = Arrays.asList("/salary", "salary-config", "salary-search", "attendance-summary", "salary-input",
                "social-fund", "special-additional", "compute-execute", "salary-verify", "salary-offer", "salary-over");

        return getRoutes(permissionRoutes, iconRoutes, componentsRoutes, chartsRoutes, nestedRoutes, tableRoutes,
                exampleRoutes, tabRoutes, errorRoutes, errorLogRoutes, excelRoutes, zipRoutes, pdfRoutes, themeRoutes,
                clipboardRoutes, externalLinkRoutes, employeeRoutes, salaryRoutes);
    }

    private List<String> getRouteNames(){
        List<String> permissionRoutes = Arrays.asList("Permission", "PagePermission", "DirectivePermission", "RolePermission");
        List<String> iconRoutes = Arrays.asList("icon", "Icons");
        List<String> componentsRoutes = Arrays.asList("ComponentDemo", "TinymceDemo", "MarkdownDemo", "JsonEditorDemo", "SplitpaneDemo",
                "AvatarUploadDemo", "DropzoneDemo", "StickyDemo", "CountToDemo", "ComponentMixinDemo", "BackToTopDemo", "DragDialogDemo",
                "DragSelectDemo", "DndListDemo", "DragKanbanDemo");
        List<String> chartsRoutes = Arrays.asList("Charts", "KeyboardChart", "LineChart", "MixChart");
        List<String> nestedRoutes = Arrays.asList("Nested", "Menu1", "Menu1-1", "Menu1-2", "Menu1-2-1", "Menu1-2-2", "Menu1-3", "Menu2");
        List<String> tableRoutes = Arrays.asList("Table", "DynamicTable", "DragTable", "InlineEditTable", "ComplexTable");
        List<String> exampleRoutes = Arrays.asList("Example", "CreateArticle", "EditArticle", "ArticleList");
        List<String> tabRoutes = Arrays.asList("tab", "Tab");
        List<String> errorRoutes = Arrays.asList("ErrorPages", "Page401", "Page404");
        List<String> errorLogRoutes = Arrays.asList("error-log", "ErrorLog");
        List<String> excelRoutes = Arrays.asList("Excel", "ExportExcel", "SelectExcel", "MergeHeader", "UploadExcel");
        List<String> zipRoutes = Arrays.asList("Zip", "ExportZip");
        List<String> pdfRoutes = Arrays.asList("pdf", "PDF", "pdf-download");
        List<String> themeRoutes = Arrays.asList("theme", "Theme");
        List<String> clipboardRoutes = Arrays.asList("clipboard", "ClipboardDemo");
        List<String> externalLinkRoutes = Arrays.asList("external-link", "VueElementAdmin");
        List<String> employeeRoutes = Arrays.asList("EmployeeComponent", "EmployeeList", "EmployeeAdd");
        List<String> salaryRoutes = Arrays.asList("SalaryComponent", "SalaryConfig", "SalarySearch", "AttendanceSummary", "SalaryInput",
                "SocialFund", "SpecialAdditional", "ComputeExecute", "SalaryVerify", "SalaryOffer", "SalaryOver");

        return getRoutes(permissionRoutes, iconRoutes, componentsRoutes, chartsRoutes, nestedRoutes, tableRoutes, exampleRoutes,
                tabRoutes, errorRoutes, errorLogRoutes, excelRoutes, zipRoutes, pdfRoutes, themeRoutes, clipboardRoutes,
                externalLinkRoutes, employeeRoutes, salaryRoutes);
    }
}
