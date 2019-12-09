package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.LocaleTranslation;

/**
 * The Interface LocaleTranslationDao.
 */
@Repository("LocaleTranslationDao")
public interface LocaleTranslationDao extends JpaRepository<LocaleTranslation, Integer> {
	
	/**
	 * 找出對應功能的多語系資料.
	 *
	 * @param refSeq the ref seq
	 * @param FunctionType the function type
	 * @return the list
	 */
	List<LocaleTranslation> findByRefSeqAndFunctionType(Integer refSeq, String FunctionType);
	
	/**
	 * 刪除對應功能的多語系資料.
	 *
	 * @param refSeq the ref seq
	 * @param functionType the function type
	 */
    @Modifying
    @Query(value = "DELETE FROM locale_translation WHERE ref_seq=:refSeq AND function_type=:functionType", nativeQuery = true)
    void deleteByRefSeqAndFunctionType(@Param(value = "refSeq") Integer refSeq, @Param(value = "functionType") String functionType);

//	@Modifying
//	@Query("delete from LocaleTranslation l where l.refSeq=:refSeq and l.functionType=:functionType")
//	void deleteByRefSeqAndFunctionType(@Param("refSeq") Integer refSeq, @Param("functionType") String functionType);
}
