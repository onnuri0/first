package com.kyw.study.first.generator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RnfTbIdGenerator implements IdentifierGenerator{

	private static AtomicInteger atomicKey = new AtomicInteger(0);
	private static Logger LOG = LoggerFactory.getLogger(RnfTbIdGenerator.class);
	
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		// 해당클래스명 앞의 3자리 + _ + 날짜시분초 + _ +  atomicKey
		
		
		Calendar calc = Calendar.getInstance();
		
		String cName = obj.getClass().getSimpleName();
		String sName = cName.toUpperCase().replaceAll("ANBTECH", "");		
		LOG.info("[CNAME IS] {} || {}", cName, sName);
		String prefix = sName.substring(0,3).toUpperCase();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = formatter.format(calc.getTime());
		String genKey = prefix+"_"+now +""+ atomicKey.incrementAndGet();
		
		return genKey;
	}

}