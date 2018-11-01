package com.example.demo.dao;

        import java.util.List;

        import com.example.demo.entity.Bills;
        import com.example.demo.entity.DomesticDataBills;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;


public interface DomesticDataBillsDao extends JpaRepository<DomesticDataBills, Integer> {
    public List<DomesticDataBills> findAll();

 //   @Query(value="select domesticdatabills from domesticdatabills where number=?1")
    public List<DomesticDataBills> findByNumber(String number);

  //  @Query(value="select domesticdatabills from domesticdatabills where number=?1 and starttime=?2")
   // public List<DomesticDataBills> findByNumberAndStartTime(String number, String startTime);

    public DomesticDataBills save(DomesticDataBills domesticDataBill);

    public void delete(DomesticDataBills domesticDataBill);
}