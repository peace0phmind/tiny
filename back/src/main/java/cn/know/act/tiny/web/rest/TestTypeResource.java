package cn.know.act.tiny.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.criteria.TestTypeCriteria;
import cn.know.act.tiny.service.criteria.TestTypeQueryService;
import cn.know.act.tiny.service.dto.TestTypeDTO;
import cn.know.act.tiny.service.inf.TestTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link cn.know.act.tiny.domain.TestType }.
 */
@RequestMapping("/api")
@RestController("TnyTestTypeResource")
public class TestTypeResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "TestType";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestTypeResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeService testTypeService;

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeQueryService testTypeQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public TestTypeResource(TestTypeService testTypeService, TestTypeQueryService testTypeQueryService) {
        this.testTypeService = testTypeService;
        this.testTypeQueryService = testTypeQueryService;
    }

    /**
     * {@code POST  /tny-test-types : Create a new TestType.
     *
     * @param testTypeDTO the TestType to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new TestType, or with status {@code 400 (Bad Request)} if the TestType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建测试类型")
    @PostMapping("/tny-test-types")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTestType_CREATE')")
    public ResponseEntity<TestTypeDTO> createTestType(@Valid @RequestBody TestTypeDTO testTypeDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create TestType: {}", testTypeDTO);
        }
        if (testTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new TestType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestTypeDTO result = testTypeService.create(testTypeDTO);
        return ResponseEntity.created(new URI("/api/tny-test-types/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /tny-test-types/:id} : get the "id" TestType.
     *
     * @param id the id of the TestType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the TestType, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个测试类型")
    @GetMapping("/tny-test-types/{id}")
    @PreAuthorize("hasPermission(#id, '', 'TnyTestType_READ')")
    public ResponseEntity<TestTypeDTO> getTestType(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<TestTypeDTO> testTypeDTO = testTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testTypeDTO);
    }

    /**
     * {@code GET  /tny-test-types} : get all the TestTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of TestTypes in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试类型列表")
    @GetMapping("/tny-test-types")
    @PreAuthorize("hasPermission(#criteria, 'TnyTestType_READ')")
    public ResponseEntity<Page<TestTypeDTO>> findTestTypes(TestTypeCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get TestTypes");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(testTypeQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /tny-test-types/count} : count all the TestTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试类型数量")
    @GetMapping("/tny-test-types/count")
    @PreAuthorize("hasPermission(#criteria, 'TnyTestType_READ')")
    public ResponseEntity<Long> countTestTypes(TestTypeCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(testTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /tny-test-types } : Updates an existing TestType.
     *
     * @param testTypeDTO the TestType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated TestType,
     * or with status {@code 400 (Bad Request)} if the TestType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the TestType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新测试类型")
    @PutMapping("/tny-test-types")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTestType_UPDATE')")
    public ResponseEntity<TestTypeDTO> updateTestType(@Valid @RequestBody TestTypeDTO testTypeDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update TestType: {}", testTypeDTO);
        }
        if (testTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestTypeDTO result = testTypeService.update(testTypeDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /tny-test-types/:id} : delete the "ids" TestType.
     *
     * @param ids the ids of the TestTypes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除测试类型")
    @DeleteMapping("/tny-test-types/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'TnyTestType_DELETE')")
    public ResponseEntity<Void> deleteTestType(@PathVariable List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete TestType : {}", ids);
        }
        testTypeService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
