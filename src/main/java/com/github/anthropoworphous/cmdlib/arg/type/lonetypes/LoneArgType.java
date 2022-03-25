package com.github.anthropoworphous.cmdlib.arg.type.lonetypes;

import com.github.anthropoworphous.cmdlib.arg.parser.IArgParser;
import com.github.anthropoworphous.cmdlib.arg.parser.ModifiableParser;
import com.github.anthropoworphous.cmdlib.arg.type.ArgType;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface LoneArgType<T> extends ArgType<T> {
    Optional<T> stringToArgType(String input);

    String argTypeToString(T input);

    /**
     * A quick way to edit without having to set any var
     *
     * @param modifier the consumer to edit the modifier
     * @return this ArgType
     */
    default ArgType<T> parser(Consumer<ModifiableParser<T>> modifier) {
        modifier.accept(parser());
        return this;
    }

    /**
     * Get the parser of this argType instance
     * You can edit the parser from this, it'll have an effect
     *
     * @return The parser
     */
    IArgParser<T> parser();

    @Override
    default List<T> whitelist() {
        return parser().getWhitelist();
    }

    @Override
    default List<T> blacklist() {
        return parser().getBlacklist();
    }
}
