package com.neux.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class SMSHttpService {
    private String sendSMSUrl = "http://api.every8d.com/API21/HTTP/sendSMS.ashx";
    private String getCreditUrl = "http://api.every8d.com/API21/HTTP/getCredit.ashx";
    private String processMsg = "";
    private String batchID = "";
    private double credit = 0;

    @Value("${every8d.userid:0975035390}")
    private String userID;

    @Value("${every8d.userpwd:1q@W3e$R5t}")
    private String userPwd;


    public static void main(String[] args) {
        SMSHttpService smsHttpService = new SMSHttpService();
        smsHttpService.sendSMS("0975035390","1q@W3e$R5t","測試主旨","測試內容","0975035390","");

    }

    public boolean sendSMS(String subject, String content, String mobile) {
        boolean success = false;
        try {
            StringBuilder postDataSb = new StringBuilder();
            postDataSb.append("UID=").append(userID);
            postDataSb.append("&PWD=").append(userPwd);
            postDataSb.append("&SB=").append(subject);
            postDataSb.append("&MSG=").append(content);
            postDataSb.append("&DEST=").append(mobile);
            postDataSb.append("&ST=").append("");

            String resultString = this.httpPost(this.sendSMSUrl, postDataSb.toString());
            if (!resultString.startsWith("-")) {
                /*
                 * �ǰe���\ �^�Ǧr�ꤺ�e�榡���GCREDIT,SENDED,COST,UNSEND,BATCH_ID�A�U�Ȥ����H�r�����j�C
                 * CREDIT�G�o�e��Ѿl�I�ơC�t�ȥN��o�e���ѡA�t�εL�k�B�z�өR�O
                 * SENDED�G�o�e�q�ơC
                 * COST�G�����o�e�����I��
                 * UNSEND�G�L�B�׮ɵo�e���q�ơA��ӭȤj��0�ӳѾl�I�Ƶ���0�ɪ�ܦ�������²�T�]�L�B�צӵL�k�Q�o�e�C
                 * BATCH_ID�G�妸�ѧO�N�X�C���@�ߤ@�ѧO�X�A�i�ǥѥ��ѧO�X�d�ߵo�e���A�C�榡�d�ҡG220478cc-8506-49b2-93b7-2505f651c12e
                 */
                String[] split = resultString.split(",");
                this.credit = Double.parseDouble(split[0]);
                this.batchID = split[4];
                success = true;
            } else {
                //�ǰe����
                this.processMsg = resultString;
            }

        } catch (Exception ex) {
            this.processMsg = ex.getMessage();
        }
        return success;
    }

    /// <summary>
    /// ���o�b���l�B
    /// </summary>
    /// <param name="userID">�b��</param>
    /// <param name="password">�K�X</param>
    /// <returns>true:���o���\�Ffalse:���o����</returns>
    public boolean getCredit(String userID, String password) {
        boolean success = false;
        try {
            StringBuilder postDataSb = new StringBuilder();
            postDataSb.append("UID=").append(userID);
            postDataSb.append("&PWD=").append(password);

            String resultString = this.httpPost(this.getCreditUrl, postDataSb.toString());
            if (!resultString.startsWith("-")) {
                this.credit = Double.parseDouble(resultString);
                success = true;
            } else {
                this.processMsg = resultString;
            }
        } catch (Exception ex) {
            this.processMsg = ex.getMessage();
        }
        return success;
    }

    public boolean sendSMS(String userID, String password, String subject, String content, String mobile, String sendTime) {
        boolean success = false;
        try {
            StringBuilder postDataSb = new StringBuilder();
            postDataSb.append("UID=").append(userID);
            postDataSb.append("&PWD=").append(password);
            postDataSb.append("&SB=").append(subject);
            postDataSb.append("&MSG=").append(content);
            postDataSb.append("&DEST=").append(mobile);
            postDataSb.append("&ST=").append(sendTime);

            String resultString = this.httpPost(this.sendSMSUrl, postDataSb.toString());
            if (!resultString.startsWith("-")) {
                /*
                 * �ǰe���\ �^�Ǧr�ꤺ�e�榡���GCREDIT,SENDED,COST,UNSEND,BATCH_ID�A�U�Ȥ����H�r�����j�C
                 * CREDIT�G�o�e��Ѿl�I�ơC�t�ȥN��o�e���ѡA�t�εL�k�B�z�өR�O
                 * SENDED�G�o�e�q�ơC
                 * COST�G�����o�e�����I��
                 * UNSEND�G�L�B�׮ɵo�e���q�ơA��ӭȤj��0�ӳѾl�I�Ƶ���0�ɪ�ܦ�������²�T�]�L�B�צӵL�k�Q�o�e�C
                 * BATCH_ID�G�妸�ѧO�N�X�C���@�ߤ@�ѧO�X�A�i�ǥѥ��ѧO�X�d�ߵo�e���A�C�榡�d�ҡG220478cc-8506-49b2-93b7-2505f651c12e
                 */
                String[] split = resultString.split(",");
                this.credit = Double.parseDouble(split[0]);
                this.batchID = split[4];
                success = true;
            } else {
                //�ǰe����
                this.processMsg = resultString;
            }

        } catch (Exception ex) {
            this.processMsg = ex.getMessage();
        }
        return success;
    }

    private String httpPost(String url, String postData) {
        String result = "";
        try {
            URL u=new URL(url);
            HttpURLConnection conn=(HttpURLConnection) u.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));

            osw.write(postData);
            osw.flush();
            osw.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder sb=new StringBuilder();
            for (line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line);
            }
            conn.disconnect();
            result=sb.toString();
        } catch (Exception ex) {
            this.processMsg = ex.getMessage();
        }
        return result;
    }

    public String getProcessMsg() {
        return processMsg;
    }

    public String getBatchID() {
        return batchID;
    }

    public double getCreditValue() {
        return credit;
    }
}
