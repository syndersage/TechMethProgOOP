package tmp.oop;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.converters.PathConverter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Args {

  @Parameter(names = {"-h", "--help"},
      help = true)
  public boolean help;

  @Parameter(names = {"-i", "--input"}, converter = PathConverter.class, validateWith = {
      FileExistsValidator.class,
      FileReadableValidator.class}, required = true, description = "File for parsing information about wisdom")
  public Path inPath;

  @Parameter(names = {"-o", "--output"}, converter = PathConverter.class, validateWith = {
      FileExistsValidator.class,
      FileWriteableValidator.class}, required = true, description = "File for writing parsed information about wisdom")
  public Path outPath;

  @Parameter(names = {"-s", "--sort"},
      description = "Sort parsed wisdom by number of punctuation marks")
  public boolean sort;

  @Parameter(names = {"-p", "--pairs"},
      description = "Add in output file iterated information about every wisdom pair type")
  public boolean pair;

  @Parameter(names = {"-v", "--verbose", "--log"},
      description = "Output in console information about program executing")
  public boolean verbose;


  public static class FileExistsValidator implements IParameterValidator {

    @Override
    public void validate(String s, String s1) throws ParameterException {
      Path path = Path.of(s1);
      if (!Files.exists(path)) {
        throw new ParameterException("File does not exists: " + path);
      }
    }
  }

  public static class FileReadableValidator implements IParameterValidator {

    @Override
    public void validate(String s, String s1) throws ParameterException {
      Path path = Path.of(s1);
      if (!Files.isReadable(path)) {
        throw new ParameterException("Cannot read from file: " + path);
      }
    }
  }

  public static class FileWriteableValidator implements IParameterValidator {

    @Override
    public void validate(String s, String s1) throws ParameterException {
      Path path = Path.of(s1);
      if (!Files.isWritable(path)) {
        throw new ParameterException("Cannot write into file: " + path);
      }
    }
  }
}
