import static java.lang.String.join;

void main(String[] args) {
    println("Hello " + (args.length > 0 ? join(" ", args) : "World"));
}

