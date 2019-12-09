package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the blocksize_setting database table.
 * 
 */
@Entity
@Table(name="blocksize_setting")
@NamedQuery(name="BlocksizeSetting.findAll", query="SELECT b FROM BlocksizeSetting b")
public class BlocksizeSetting  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int blockSize_seq;

	@Column(name="block_name")
	private String blockName;

	private int size;

	public BlocksizeSetting() {
	}

	public int getBlockSize_seq() {
		return this.blockSize_seq;
	}

	public void setBlockSize_seq(int blockSize_seq) {
		this.blockSize_seq = blockSize_seq;
	}

	public String getBlockName() {
		return this.blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}