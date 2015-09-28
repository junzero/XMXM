

/*******************************************************************************
 * $Header: /cvsroot/EOS6V/develop/core/studio/com.primeton.studio.entity.ui/src/com/primeton/studio/entity/sdocode/SDOClass.java,v 1.4 2008/10/30 07:52:46 liu-jun Exp $
 * $Revision: 1.4 $
 * $Date: 2008/10/30 07:52:46 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2008 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on $Date: 2008/10/30 07:52:46 $
 *******************************************************************************/

package com.primeton.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.gotop.util.SingleValueDateConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AbfTDbsybImpl</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VXtmc</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>IDbsybh</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VDbsymc</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>DDbrq</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VDbrbh</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VDbzy</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VDbsywebljggxx</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>CYdbz</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VCdbh</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VLyrbh</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VLyrdw</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>VLyrbm</em>}</li>
 *   <li>{@link com.primeton.purview.impl#com.primeton.purview.impl.AbfTDbsybImpl <em>Filler</em>}</li>
 * </ul>
 * </p>
 *
 */
public class abftdbsybs
{
	public final static int VXTMC = 0;
	public final static int IDBSYBH = 1;
	public final static int VDBSYMC = 2;
	public final static int DDBRQ = 3;
	public final static int VDBRBH = 4;
	public final static int VDBZY = 5;
	public final static int VDBSYWEBLJGGXX = 6;
	public final static int CYDBZ = 7;
	public final static int VCDBH = 8;
	public final static int VLYRBH = 9;
	public final static int VLYRDW = 10;
	public final static int VLYRBM = 11;
	public final static int FILLER = 12;

	public final static int SDO_PROPERTY_COUNT = 13;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * The default value of the '{@link #getVXtmc() <em>VXtmc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVXtmc()
	 */
	protected static final String VXTMC_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVXtmc() <em>VXtmc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVXtmc()
	 */
	protected String vXtmc = VXTMC_DEFAULT_;

	/**
	 * The default value of the '{@link #getIDbsybh() <em>IDbsybh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDbsybh()
	 */
	protected static final long IDBSYBH_DEFAULT_ = 0l;

	/**
	 * The cached value of the '{@link #getIDbsybh() <em>IDbsybh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDbsybh()
	 */
	protected long iDbsybh = IDBSYBH_DEFAULT_;

	/**
	 * The default value of the '{@link #getVDbsymc() <em>VDbsymc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbsymc()
	 */
	protected static final String VDBSYMC_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVDbsymc() <em>VDbsymc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbsymc()
	 */
	protected String vDbsymc = VDBSYMC_DEFAULT_;

	/**
	 * The default value of the '{@link #getDDbrq() <em>DDbrq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDDbrq()
	 */
	protected static final Date DDBRQ_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getDDbrq() <em>DDbrq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDDbrq()
	 */
	protected Date dDbrq = DDBRQ_DEFAULT_;

	/**
	 * The default value of the '{@link #getVDbrbh() <em>VDbrbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbrbh()
	 */
	protected static final String VDBRBH_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVDbrbh() <em>VDbrbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbrbh()
	 */
	protected String vDbrbh = VDBRBH_DEFAULT_;

	/**
	 * The default value of the '{@link #getVDbzy() <em>VDbzy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbzy()
	 */
	protected static final String VDBZY_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVDbzy() <em>VDbzy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbzy()
	 */
	protected String vDbzy = VDBZY_DEFAULT_;

	/**
	 * The default value of the '{@link #getVDbsywebljggxx() <em>VDbsywebljggxx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbsywebljggxx()
	 */
	protected static final String VDBSYWEBLJGGXX_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVDbsywebljggxx() <em>VDbsywebljggxx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVDbsywebljggxx()
	 */
	protected String vDbsywebljggxx = VDBSYWEBLJGGXX_DEFAULT_;

	/**
	 * The default value of the '{@link #getCYdbz() <em>CYdbz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCYdbz()
	 */
	protected static final String CYDBZ_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getCYdbz() <em>CYdbz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCYdbz()
	 */
	protected String cYdbz = CYDBZ_DEFAULT_;

	/**
	 * The default value of the '{@link #getVCdbh() <em>VCdbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVCdbh()
	 */
	protected static final String VCDBH_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVCdbh() <em>VCdbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVCdbh()
	 */
	protected String vCdbh = VCDBH_DEFAULT_;

	/**
	 * The default value of the '{@link #getVLyrbh() <em>VLyrbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrbh()
	 */
	protected static final String VLYRBH_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVLyrbh() <em>VLyrbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrbh()
	 */
	protected String vLyrbh = VLYRBH_DEFAULT_;

	/**
	 * The default value of the '{@link #getVLyrdw() <em>VLyrdw</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrdw()
	 */
	protected static final String VLYRDW_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVLyrdw() <em>VLyrdw</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrdw()
	 */
	protected String vLyrdw = VLYRDW_DEFAULT_;

	/**
	 * The default value of the '{@link #getVLyrbm() <em>VLyrbm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrbm()
	 */
	protected static final String VLYRBM_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getVLyrbm() <em>VLyrbm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLyrbm()
	 */
	protected String vLyrbm = VLYRBM_DEFAULT_;

	/**
	 * The default value of the '{@link #getFiller() <em>Filler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiller()
	 */
	protected static final String FILLER_DEFAULT_ = null;

	/**
	 * The cached value of the '{@link #getFiller() <em>Filler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiller()
	 */
	protected String filler = FILLER_DEFAULT_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVXtmc()
	{
		return this.vXtmc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVXtmc(String vXtmc)
	{
		this.vXtmc = vXtmc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public long getIDbsybh()
	{
		return this.iDbsybh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setIDbsybh(long iDbsybh)
	{
		this.iDbsybh = iDbsybh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVDbsymc()
	{
		return this.vDbsymc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVDbsymc(String vDbsymc)
	{
		this.vDbsymc = vDbsymc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public Date getDDbrq()
	{
		return this.dDbrq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setDDbrq(Date dDbrq)
	{
//		SimpleDateFormat simFormat = new SimpleDateFormat(format,Locale.CHINA); 
		this.dDbrq = dDbrq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVDbrbh()
	{
		return this.vDbrbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVDbrbh(String vDbrbh)
	{
		this.vDbrbh = vDbrbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVDbzy()
	{
		return this.vDbzy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVDbzy(String vDbzy)
	{
		this.vDbzy = vDbzy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVDbsywebljggxx()
	{
		return this.vDbsywebljggxx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVDbsywebljggxx(String vDbsywebljggxx)
	{
		this.vDbsywebljggxx = vDbsywebljggxx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getCYdbz()
	{
		return this.cYdbz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setCYdbz(String cYdbz)
	{
		this.cYdbz = cYdbz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVCdbh()
	{
		return this.vCdbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVCdbh(String vCdbh)
	{
		this.vCdbh = vCdbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVLyrbh()
	{
		return this.vLyrbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVLyrbh(String vLyrbh)
	{
		this.vLyrbh = vLyrbh;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVLyrdw()
	{
		return this.vLyrdw;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVLyrdw(String vLyrdw)
	{
		this.vLyrdw = vLyrdw;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getVLyrbm()
	{
		return this.vLyrbm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setVLyrbm(String vLyrbm)
	{
		this.vLyrbm = vLyrbm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getFiller()
	{
		return this.filler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setFiller(String filler)
	{
		this.filler = filler;
	}

  	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
	{
		StringBuffer result = new StringBuffer(super.toString());
	
		result.append(" (vXtmc: ");
		result.append(vXtmc);
	
		result.append(", iDbsybh: ");
		result.append(iDbsybh);
	
		result.append(", vDbsymc: ");
		result.append(vDbsymc);
	
		result.append(", dDbrq: ");
		result.append(dDbrq);
	
		result.append(", vDbrbh: ");
		result.append(vDbrbh);
	
		result.append(", vDbzy: ");
		result.append(vDbzy);
	
		result.append(", vDbsywebljggxx: ");
		result.append(vDbsywebljggxx);
	
		result.append(", cYdbz: ");
		result.append(cYdbz);
	
		result.append(", vCdbh: ");
		result.append(vCdbh);
	
		result.append(", vLyrbh: ");
		result.append(vLyrbh);
	
		result.append(", vLyrdw: ");
		result.append(vLyrdw);
	
		result.append(", vLyrbm: ");
		result.append(vLyrbm);
	
		result.append(", filler: ");
		result.append(filler);
  		result.append(')');
  		return result.toString();
	}
}