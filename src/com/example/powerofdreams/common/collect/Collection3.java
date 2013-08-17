package com.example.powerofdreams.common.collect;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Utility functions related to collections. This class covers functionalities that
 * Guava library (com.google.common.collect.Collection2) does not supports.
 * 
 * @author tayama09324@gmail.com (Takashi Tayama)
 */
public class Collection3 {

	// Not instantiable
	private Collection3() {}

	public static <T> List<T> unique(List<T> list) {
		return Lists.newArrayList(Sets.newHashSet(list));
	}
}
