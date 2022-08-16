package shop.gaship.payment.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;
import shop.gaship.payment.history.entity.CardPayment;
import shop.gaship.payment.history.entity.EasyPayment;
import shop.gaship.payment.history.entity.PaymentHistory;
import shop.gaship.payment.history.exception.PaymentHistoryNotFoundException;
import shop.gaship.payment.history.repository.CardPaymentRepository;
import shop.gaship.payment.history.repository.EasyPaymentRepository;
import shop.gaship.payment.history.repository.PaymentHistoryRepository;
import shop.gaship.payment.history.service.PaymentHistoryService;

/**
 * 결제이력 service interface 구현체입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {
    private final CardPaymentRepository cardPaymentRepository;
    private final EasyPaymentRepository easyPaymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    @Transactional
    public void addPaymentHistory(CardPaymentHistoryRequest cardPaymentHistoryRequest) {
        CardPayment cardPayment = dtoToEntity(
                cardPaymentHistoryRequest);

        cardPaymentRepository.save(cardPayment);
    }

    @Override
    @Transactional
    public void addPaymentHistory(EasyPaymentHistoryRequest easyPaymentHistoryRequest) {
        EasyPayment easyPayment = dtoToEntity(
                easyPaymentHistoryRequest);

        easyPaymentRepository.save(easyPayment);
    }

    @Override
    public PaymentHistory findPaymentHistory(String paymentKey) {
        return paymentHistoryRepository
                .findById(paymentKey)
                .orElseThrow(PaymentHistoryNotFoundException::new);
    }
}
