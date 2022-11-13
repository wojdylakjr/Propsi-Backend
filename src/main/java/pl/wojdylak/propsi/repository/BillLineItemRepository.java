package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Bill;
import pl.wojdylak.propsi.model.BillLineItem;

public interface BillLineItemRepository  extends JpaRepository<BillLineItem, Long> {
}
