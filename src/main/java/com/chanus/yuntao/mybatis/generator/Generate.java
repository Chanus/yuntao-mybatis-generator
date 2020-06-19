/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成 Mybatis 代码
 *
 * @author Chanus
 * @date 2018-09-01 12:50:45
 * @since 1.0.0
 */
public class Generate {
    /**
     * 自动生成 Mybatis 代码
     *
     * @param cfgPath Mybatis 配置文件路径，默认放到 src 下面
     * @since 1.0.0
     */
    public static void generator(String cfgPath) {
        List<String> warnings = new ArrayList<>();
        /* String cfgPath = "/generatorConfig.xml";// 配置文件的路径，默认放到src下面 */
        URL url = Generate.class.getResource(cfgPath);
        String file = url.getFile();
        File configFile = new File(file);
        generator(configFile, warnings);
    }

    /**
     * 自动生成 Mybatis 代码
     *
     * @param configFile Mybatis 配置文件
     * @since 1.0.0
     */
    public static void generator(File configFile) {
        List<String> warnings = new ArrayList<>();
        generator(configFile, warnings);
    }

    /**
     * 自动生成 Mybatis 代码
     *
     * @param configFile Mybatis 配置文件
     * @param warnings   异常信息
     * @since 1.0.0
     */
    private static void generator(File configFile, List<String> warnings) {
        ConfigurationParser cfgParser = new ConfigurationParser(warnings);// 配置文件解析器
        Configuration config = null;
        try {
            config = cfgParser.parseConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (config == null) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>config is null");
            return;
        }
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = null;
        try {
            generator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        if (generator == null) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>generator is null");
            return;
        }
        try {
            generator.generate(null);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>Mybatis code generate success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String cfgPath = "/generatorConfig.xml";// 配置文件的路径，默认放到src下面
        Generate.generator(cfgPath);
    }
}
