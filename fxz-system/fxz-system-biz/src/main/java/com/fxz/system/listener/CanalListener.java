package com.fxz.system.listener;

import com.fxz.common.canal.CanalBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author fxz
 */
@Slf4j
//@Component
@RequiredArgsConstructor
public class CanalListener {

    private final CanalBase canalBase;

    @RabbitListener(queues = "canal_queue")
    public void onMessage(Message message) {
        log.info("message:{}", message);
        canalBase.process(new String(message.getBody()));
    }

}