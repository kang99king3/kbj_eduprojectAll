package com.hk.board.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecute {
	/*
	 * JoinPoint에서 제공하는 메서드
	 *   getArgs():메서드의 아규먼트를 반환
	 *   getTarget():대상 객체를 반환
	 *   getSignature(): 호출되는 메서드에 대한 정보
	 *       --> getName():메서드의 이름을 구함
	 *       --> toLongName() : 메서드의 풀네임(메서드의 리턴타입, 파라미터 타입 모두 표시)
	 *       --> toShortName() : 메서드를 축약해서 표현(메서드이름만)
	 */
	
	public void before(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.debug("debug:시작");
		log.info("info:시작 -"+join.getTarget()+"");
		log.info("info:시작 -"+join.getSignature().getName());
		for (int i = 0; i < join.getArgs().length; i++) {
			log.info(join.getArgs()[i]+"");			
		}
	}
	
//	@Around("publicTest()")
	public void afterReturning(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.debug("debug:끝");
		log.info("info:끝");
	}
}






