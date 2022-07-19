/*
 * Copyright 2018-2022 simple-syslog authors
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.palindromicity.syslog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AbstractRfc5425SyslogParserTest {

  protected static List<Map<String, Object>> handleFile(String fileName, SyslogParser<Map<String, Object>> parser) throws Exception {
    try (Reader reader = new BufferedReader(new FileReader(new File(fileName)))) {
      return parser.parseLines(reader);
    }
  }

  protected static void handleFile(String fileName, SyslogParser<Map<String, Object>> parser, Consumer<Map<String, Object>> consumer)
      throws Exception {
    try (Reader reader = new BufferedReader(new FileReader(new File(fileName)))) {
      parser.parseLines(reader, consumer);
    }
  }

  protected static void handleFile(String fileName, SyslogParser<Map<String, Object>> parser, Consumer<Map<String, Object>> consumer,
      BiConsumer<String,Throwable> errorConsumer)
      throws Exception {
    try (Reader reader = new BufferedReader(new FileReader(new File(fileName)))) {
      parser.parseLines(reader, consumer, errorConsumer);
    }
  }

  protected static Map<String, Object> handleLine(String line, SyslogParser<Map<String, Object>> parser) throws Exception {
    return parser.parseLine(line);
  }

  protected static void handleLine(String line, SyslogParser<Map<String, Object>> parser, Consumer<Map<String, Object>> consumer)
      throws Exception {
    parser.parseLine(line, consumer);
  }

  protected static void handleFlatLine(String line, SyslogParser<Map<String, String>> parser, Consumer<Map<String, String>> consumer)
      throws Exception {
    parser.parseLine(line, consumer);
  }
}
