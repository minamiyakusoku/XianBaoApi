package com.stxb.utils.shantu.encode;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.mail.internet.NewsAddress;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stxb.service.data.SysEncodeService;
import com.stxb.service.data.impl.SysEncodeServiceImpl;
import com.stxb.utils.encode.EncodeHelper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc-servlet.xml")
public class TestEncodeHelper extends AbstractTransactionalJUnit4SpringContextTests{
	@Override
	@Resource(name = "dataSource1")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	@Autowired
	SysEncodeService ser;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecode() throws Exception {
		EncodeHelper.decode("ejjH5fEpcd/SZpILwi5He3L5PSWDd0Tp", ser.get(17));
	}

	@Test
	public void testEncode() throws Exception {
		EncodeHelper.encode("kawaii", ser.get(19));
	}

}
