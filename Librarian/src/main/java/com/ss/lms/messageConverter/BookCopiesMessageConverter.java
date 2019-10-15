package com.ss.lms.messageConverter;

import java.io.IOException;
import java.io.OutputStream;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.ss.lms.entity.BookCopies;

public class BookCopiesMessageConverter extends AbstractHttpMessageConverter<BookCopies>{

	public BookCopiesMessageConverter() {
		super(new MediaType("text","bookCopies"));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BookCopies.class.isAssignableFrom(clazz);
	}

	@Override
	protected BookCopies readInternal(Class<? extends BookCopies> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(BookCopies bookCopies, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		OutputStream outputStream = outputMessage.getBody();
		String body = "Book Id: "+bookCopies.getBook().getBookId()+"\n"
				+ "Book Name: "+ bookCopies.getBook().getTitle()+"\n"
				+ "Author Name: "+ bookCopies.getBook().getAuthor().getAuthorName()+"\n"
				+ "Publisher Name: "+ bookCopies.getBook().getPublisher().getPublisherName()+ "\n"
				+ "Branch Name: " + bookCopies.getLibraryBranch().getBranchName() +"\n"
				+ "Number of copies in this branch: "+ bookCopies.getNoOfCopies()+";\n" ;
		
		outputStream.write(body.getBytes());
		outputStream.close();
		
	}
	
//	protected void writeInternal(List<BookCopies> bookCopies, HttpOutputMessage outputMessage)
//			throws IOException, HttpMessageNotWritableException {
//		
//		OutputStream outputStream = outputMessage.getBody();
//		bookCopies.stream().forEach(x ->
//		{
//			try {
//				outputStream.write( ("Book Id: "+x.getBook().getBookId()+"\n"
//						+ "Book Name: "+ x.getBook().getTitle()+"\n"
//						+ "Author Name: "+ x.getBook().getAuthor().getAuthorName()+"\n"
//						+ "Publisher Name: "+ x.getBook().getPublisher().getPublisherName()+ "\n"
//						+ "Branch Name: " + x.getLibraryBranch().getBranchName() +"\n"
//						+ "Number of copies in this branch: "+ x.getNoOfCopies()+";\n").getBytes() );
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} );
//		
//		
//		outputStream.close();
//		
//	}

}
