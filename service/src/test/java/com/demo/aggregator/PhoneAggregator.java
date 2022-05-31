package com.demo.aggregator;

import com.demo.common.domain.Phone;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class PhoneAggregator implements ArgumentsAggregator {
    @Override
    public Phone aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        Phone phone = new Phone(arguments.getString(0));
        phone.setCpu(arguments.getString(1));
        return phone;
    }
}
