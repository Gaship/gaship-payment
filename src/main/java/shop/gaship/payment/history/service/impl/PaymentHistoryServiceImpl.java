package shop.gaship.payment.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.payment.history.dto.request.impl.CardPaymentHistoryRequest;
import shop.gaship.payment.history.dto.request.impl.EasyPaymentHistoryRequest;
import shop.gaship.payment.history.entity.CardPayment;
import shop.gaship.payment.history.entity.EasyPayment;
import shop.gaship.payment.history.repository.CardPaymentRepository;
import shop.gaship.payment.history.repository.EasyPaymentRepository;
import shop.gaship.payment.history.service.PaymentHistoryService;
import shop.gaship.payment.status.entity.PaymentStatusCode;
import shop.gaship.payment.status.enumm.PaymentStatus;
import shop.gaship.payment.status.service.PaymentStatusService;

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
    private final PaymentStatusService paymentStatusService;

    @Override
    @Transactional
    public void addPaymentHistory(CardPaymentHistoryRequest cardPaymentHistoryRequest) {
        PaymentStatusCode paymentStatusCode = paymentStatusService
                .findPaymentStatus(PaymentStatus.SUCCESS.getValue());

        CardPayment cardPayment = dtoToEntity(
                cardPaymentHistoryRequest,
                paymentStatusCode);

        cardPaymentRepository.save(cardPayment);
    }

    @Override
    @Transactional
    public void addPaymentHistory(EasyPaymentHistoryRequest easyPaymentHistoryRequest) {
        PaymentStatusCode paymentStatusCode = paymentStatusService
                .findPaymentStatus(PaymentStatus.SUCCESS.getValue());

        EasyPayment easyPayment = dtoToEntity(
                easyPaymentHistoryRequest,
                paymentStatusCode);

        easyPaymentRepository.save(easyPayment);
    }
}
