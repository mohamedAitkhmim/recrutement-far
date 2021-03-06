package com.far.recrutement.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class Liste<T> extends ArrayList<T> implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;

	public Liste<T> where(Predicate<T> predicate)
	{
		Liste<T> result=new Liste<>();
		for(T item : this)
		{
			if(predicate.test(item))
			{
				result.add(item);
			}
		}
		return result;
	}
	
	public Liste<T> limit(int start,int end)
	{
		if(end>this.size())
		{
			end = this.size();
		}
		Liste<T> r=new Liste<>();
		for(int i=start;i<end;i++)
		{
			r.add(this.get(i));
		}
		return r;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Liste<T> orderBy(Function<T,Comparable> function)
	{
		Liste<T> r=new Liste<>();
		this.forEach(r::add);
		for(int i=0;i<r.size() - 1;i++)
		{
			for(int j = i + 1;j<r.size(); j++)
			{
				if(function.apply(r.get(j)).compareTo(function.apply(r.get(i))) < 0)
				{
					T temp=r.get(i);
					r.set(i, r.get(j));
					r.set(j, temp);
				}
			}
		}
		return r;
	}
	
	@SuppressWarnings("rawtypes")
	public Liste getRandomElements(int nombre)
	{
		Liste<T> result=new Liste<>();
		if(nombre > size())
			nombre=size();
		for(int i=0;result.size()<nombre && i<100;i++)
		{
			Random rn = new Random();
			int r=rn.nextInt();
			int x = r % size();
			if(x<0)
				x=0-x;
			if(!result.contains(get(x)))
			{
				result.add(get(x));
			}
		}
		return result;
	}
	
	//from methods
	//fromIterable
	//fromPage
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  static Liste fromIterable(Iterable iterable)
	{
		Liste result=new Liste();
		if(iterable!=null)
			iterable.forEach(result::add);
		return result;
	}
	
	//to methods
	public Page<T> toPage(Pageable pageable)
	{
		return new PageImpl<>(this, pageable, this.size());
	}
	
	@SuppressWarnings("deprecation")
	public Page<T> toPage(int page,int size)
	{
		return toPage(PageRequest.of(page,size));
	}
	
	public void addIfNotExist(T item)
	{
		if(!this.contains(item))
			this.add(item);
	}
	
	public void addIfNotExist(Liste<T> items)
	{
		items.forEach(this::addIfNotExist);
	}
}
