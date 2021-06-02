package miniproject.phonebook;

import java.util.List;


public interface PhoneBookDAO {
	public List<PhoneBookVO> getList(); // 단순 SELECT
	public List<PhoneBookVO> search(String keyword); //LIKE
	public boolean insert(PhoneBookVO vo); //INSERT
	public boolean delete(Long id); // PK로 삭제
}
