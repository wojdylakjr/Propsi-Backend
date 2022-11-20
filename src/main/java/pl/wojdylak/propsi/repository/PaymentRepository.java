package pl.wojdylak.propsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.propsi.model.Owner;
import pl.wojdylak.propsi.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
