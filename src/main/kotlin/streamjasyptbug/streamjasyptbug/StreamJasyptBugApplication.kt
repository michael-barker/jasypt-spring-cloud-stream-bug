package streamjasyptbug.streamjasyptbug

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.support.MessageBuilder


@SpringBootApplication
class StreamJasyptBugApplication {
  @Bean
  fun encryptedValue(
      testSource: TestSource,
      @Value("\${encrypted.value}") value: String
  ) = CommandLineRunner {
    println("Encrypted Value: $value")
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(StreamJasyptBugApplication::class.java, *args)
}

@EnableBinding(Source::class)
class TestSource(private val source: Source) {
  @ServiceActivator(inputChannel = Sink.INPUT)
  fun send(message: String) {
    source.output().send(MessageBuilder.withPayload(message).build())
  }
}
