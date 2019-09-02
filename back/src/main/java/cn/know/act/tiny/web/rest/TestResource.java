package cn.know.act.tiny.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.criteria.TestCriteria;
import cn.know.act.tiny.service.criteria.TestQueryService;
import cn.know.act.tiny.service.dto.TestDTO;
import cn.know.act.tiny.service.inf.TestService;
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
 * REST controller for managing {@link cn.know.act.tiny.domain.Test }.
 */
@RequestMapping("/api")
@RestController("TnyTestResource")
public class TestResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Test";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestService testService;

    @Generated(IRW.CODE_GENERATOR)
    private final TestQueryService testQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public TestResource(TestService testService, TestQueryService testQueryService) {
        this.testService = testService;
        this.testQueryService = testQueryService;
    }

    /**
     * {@code POST  /tny-tests : Create a new Test.
     *
     * @param testDTO the Test to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Test, or with status {@code 400 (Bad Request)} if the Test has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建测试")
    @PostMapping("/tny-tests")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTest_CREATE')")
    public ResponseEntity<TestDTO> createTest(@Valid @RequestBody TestDTO testDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Test: {}", testDTO);
        }
        if (testDTO.getId() != null) {
            throw new BadRequestAlertException("A new Test cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestDTO result = testService.create(testDTO);
        return ResponseEntity.created(new URI("/api/tny-tests/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /tny-tests/:id} : get the "id" Test.
     *
     * @param id the id of the Test to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Test, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个测试")
    @GetMapping("/tny-tests/{id}")
    @PreAuthorize("hasPermission(#id, '', 'TnyTest_READ')")
    public ResponseEntity<TestDTO> getTest(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<TestDTO> testDTO = testService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testDTO);
    }

    /**
     * {@code GET  /tny-tests} : get all the Tests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Tests in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试列表")
    @GetMapping("/tny-tests")
    @PreAuthorize("hasPermission(#criteria, 'TnyTest_READ')")
    public ResponseEntity<Page<TestDTO>> findTests(TestCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Tests");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(testQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /tny-tests/count} : count all the Tests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试数量")
    @GetMapping("/tny-tests/count")
    @PreAuthorize("hasPermission(#criteria, 'TnyTest_READ')")
    public ResponseEntity<Long> countTests(TestCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(testQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /tny-tests } : Updates an existing Test.
     *
     * @param testDTO the Test to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Test,
     * or with status {@code 400 (Bad Request)} if the Test is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Test couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新测试")
    @PutMapping("/tny-tests")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTest_UPDATE')")
    public ResponseEntity<TestDTO> updateTest(@Valid @RequestBody TestDTO testDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Test: {}", testDTO);
        }
        if (testDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestDTO result = testService.update(testDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /tny-tests/:id} : delete the "ids" Test.
     *
     * @param ids the ids of the Tests to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除测试")
    @DeleteMapping("/tny-tests/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'TnyTest_DELETE')")
    public ResponseEntity<Void> deleteTest(@PathVariable List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Test : {}", ids);
        }
        testService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
