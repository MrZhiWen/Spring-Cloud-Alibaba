package com.mall.common.utils;


import org.apache.commons.collections4.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 发送邮件工具类
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class SendEmailUtils {

    // 发送者别名
    private static final String SENDER_NAME = "liushun";

    // 发送邮箱地址
    // private static final String SENDER_ADDRESS = "liushun@lppz.com";

    // 发送邮箱的授权码
    // private static final String SENDER_PWD = "wzlp1234*";
    // private static final String SENDER_PWD = "huakai1122@@";

    /**
     * 网易相关配置
     */
    // private static final String SENDER_ADDRESS = "jaspernzp@163.com";
    // private static final String SENDER_PWD = "CHBULQCCTLBKNIVD";

    /**
     * 阿里邮箱
     */
    private static final String SENDER_ADDRESS = "huangqipeng@lppz.com";
    private static final String SENDER_PWD = "huakai1122@@";

    // 密送的邮箱地址
    private static final String PRIVATE_ADDRESS = "huangqipeng@lppz.com";


    /**
     * 外卖组邮箱
     */
    private static final List<String> WM_EMAIL_LIST = Arrays.asList("719272090@qq.com");

    /**
     * 默认标题
     */
    public static final String EMAIL_TITLE = "配送费预警";

    /**
     * smtp端口
     */
    public static final String SMPT_PORT = "465";

    /**
     * 发送邮件的环境对象
     */
    private static final Session EMAIL_SESSION = getEmailSession();

    /**
     * 批量发送电子邮件
     *
     * @param emailAddressList 邮箱地址
     * @param content          邮件内容
     * @param title            邮件标题
     * @param fileList         附件
     * @throws Exception
     */
    public static void sendEmail(List<String> emailAddressList, String title, String content, List<File> fileList) throws Exception {
        // 默认发送外卖组
        MimeMessage mimeMessage;
        if (CollectionUtils.isEmpty(emailAddressList)) {
            mimeMessage = getMimeMessage(WM_EMAIL_LIST, title, content);
        } else {
            mimeMessage = getMimeMessage(emailAddressList, title, content);

        }
        if (!CollectionUtils.isEmpty(fileList)) {
            // 处理附件
            Multipart multipart = getMultipart(fileList);
            mimeMessage.setContent(multipart);
            //  添加邮件内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            // 将multipart对象放入message中
            multipart.addBodyPart(contentPart);
        }
        Transport.send(mimeMessage);
    }

    private static MimeMessage getMimeMessage(List<String> emailAddressList, String title, String content) throws Exception {
        // 创建邮件消息
        MimeMessage message = new MimeMessage(EMAIL_SESSION);
        // 设置发件人
        message.setFrom(new InternetAddress(SENDER_ADDRESS, SENDER_NAME));
        // 设置收件人
        InternetAddress[] address = new InternetAddress[emailAddressList.size()];
        for (int i = 0; i < emailAddressList.size(); i++) {
            address[i] = new InternetAddress(emailAddressList.get(i));
        }
        message.setRecipients(Message.RecipientType.TO, address);
        // 设置密送
//        message.setRecipient(Message.RecipientType.BCC, new InternetAddress(PRIVATE_ADDRESS));
        // 设置邮件标题
        message.setSubject(title, "UTF-8");
        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");
        // 设置发送时间
        message.setSentDate(new Date());
        return message;
    }

    private static Multipart getMultipart(List<File> fileList) {
        if (CollectionUtils.isEmpty(fileList)) return null;
        Multipart multipart = new MimeMultipart();
        // 添加附件的内容
        for (File file : fileList) {
            try {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return multipart;
    }

    private static Session getEmailSession() {
        // 配置发送邮件的环境属性
        Properties props = new Properties();
        // 启用调试
        // props.setProperty("mail.debug", "true");
        // 设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        // 设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置发件人的SMTP服务器地址
        // props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
        props.setProperty("mail.smtp.port", SMPT_PORT);
        props.setProperty("mail.smtp.socketFactory.port", SMPT_PORT);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "true");
        // props.setProperty("mail.smtp.ssl.trust", "*");

        props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
        // props.setProperty("mail.smtp.host", "smtp.163.com");
        // props.setProperty("mail.imap.host", "imap.lppz.com");
        // props.setProperty("mail.imap.port", "993");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                return new PasswordAuthentication(SENDER_ADDRESS, SENDER_PWD);
            }
        };
        return Session.getInstance(props, authenticator);
    }

    public static void main(String[] args) throws Exception {
        sendEmail(null, "测试邮件", "订单[WM2020050700000024]连续三次推送失败, 失败原因[不足明细：商家商品编码：EMG4418113782553，商品编码：EMG4418113782553，可用库存缺：4，可用库存：0，仓库ID：7670;单号【WHJDCP20200507000002】（解决方案参考：http://open.jd.com/home/home#/doc/common?listId=533）]", null);
    }

}
