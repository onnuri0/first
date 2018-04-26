package com.kyw.study.first.generator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RnfTbIdIntegerGenerator implements IdentifierGenerator{

	private static AtomicInteger atomicKey = new AtomicInteger(0);
	
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		//  날짜시분초 + _ +  atomicKey
		
		Calendar calc = Calendar.getInstance();
			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String now = formatter.format(calc.getTime());
		Integer genKey = Integer.parseInt(now+""+ atomicKey.incrementAndGet());
		
		return genKey;
	}

}
