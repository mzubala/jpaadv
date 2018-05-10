package pl.com.bottega.qma.confirmation;

public interface ConfirmationRepository {
  void save(Confirmation confirmation);

  Confirmation get(String documentNumber, Long confirmerId);
}
