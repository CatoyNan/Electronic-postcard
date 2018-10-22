package com.example.demo.Controller;

import com.example.demo.Dao.UploadDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
public class DataRestful {
//
//    @Autowired
//    private StudentServiceI studentServiceI;

//    @Autowired
//    private NewsDaoMapper newsDaoMapper;
//
//    @Autowired
//    private ElementDaoMapper elementDaoMapper;
//
//    @Autowired
//    private CardChargeDaoMapper cardChargeDaoMapper;
//
//    @Autowired
//    private BookDaoMapper bookDaoMapper;
//    @Autowired
//    private  PersonDaoMapper personDaoMapper;

//    @RequestMapping(value = "/upload",method = RequestMethod.GET)
//        public String upload(){
//            return "test";
//    }
    @Autowired
    private UploadDaoMapper uploadDaoMapper;
    /**
     * 测试
     * @param userName 用户名
     * @param password 密码
     * @return Map<String,Object>
     */
//    @ResponseBody
//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public void  test(@RequestParam("username") String username,@RequestParam("password")  String password){
//        uploadDaoMapper.addPerson(username,password);
//    }

//    /**
//     * 用户登录接口
//     * @param StudentAcount 用户名
//     * @param password 密码
//     * @return Map<String,Object>
//     */
//    @RequestMapping(value = "/userlogn",method = RequestMethod.GET)
//    public Map<String,Object> login(@RequestParam("account") String StudentAcount, @RequestParam("password") String password){
//        Map<String,Object> map=new HashMap<String, Object>();
//        Student student=studentServiceI.login(StudentAcount,password);
//        if(student!=null){
//            if(student.getStudentPassword().equals(password)){
//                map.put("state",true);
//                map.put("Student",student);
//                return map;
//            }
//        }
//        map.put("state",false);
//        map.put("Student","用户不存在");
//        return map;
//    }
//
//    /**
//     * 修改用户密码
//     * @param account
//     * @param pwd
//     * @return
//     */
//    @RequestMapping(value = "/updateStudentPwd",method = RequestMethod.POST)
//    @ResponseBody
//    public Message updateUserPwd(String account,String pwd){
//        int total=studentServiceI.updateUserPwd(account,pwd);
//        if(total>0){
//            message.setSuccess(true);
//            message.setMsg("用户密码更新成功！");
//        }else{
//            message.setSuccess(false);
//            message.setMsg("用户密码更新失败！");
//        }
//        return message;
//    }
//
//    /**
//     * 校园新闻动态标题数据接口
//     * @return
//     */
//    @RequestMapping(value = "/findAllNewsListtitleRecent",method = RequestMethod.GET)
//    @ResponseBody
//    public  Map<String,Object> findAllNewsListtitleRecent(){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("state",true);
//        map.put("reslut",newsDaoMapper.getRecentNewsListtitle());
//        return map;
//    }
//
//    /**
//     * 校园新闻动态标题数据接口(根据新闻类型返回数据)
//     * @param news_type
//     * @return
//     */
//    @RequestMapping(value = "/findAllNewsListtitleRecentByType",method = RequestMethod.GET)
//    @ResponseBody
//    public  Map<String,Object> findAllNewsListtitleRecent(@RequestParam("news_type") String news_type){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("state",true);
//        map.put("reslut",newsDaoMapper.getRecentNewsListtitleByType(news_type));
//        return map;
//    }
//
//
//
//    /**
//     * 校园新闻动态内容数据接口
//     * @return
//     */
//    @RequestMapping(value = "/findAllNewsListcontentRecent",method = RequestMethod.GET)
//    @ResponseBody
//    public  Map<String,Object> findAllNewsListcontentRecent(@RequestParam("title") String news_titles){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("state",true);
//        map.put("reslut",newsDaoMapper.getRecentNewsListContent(news_titles));
//        return map;
//    }
//
//    /**
//     * findElement 寝室电量查询数据接口
//     * @param stay_number
//     * @return
//     */
//    @RequestMapping(value = "/findElement",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> findElement(@RequestParam("stay_number") String stay_number){
//        Element element=elementDaoMapper.getElemntbyStaynumber(stay_number);
//        Map<String,Object> map=new HashMap<String,Object>();
//        if(element!=null){
//            map.put("state",true);
//            map.put("result",element);
//        }else{
//            map.put("state",false);
//            map.put("result","所属用户不存在");
//        }
//        return map;
//    }
//
//    /**
//     * 获取学生校园卡信息
//     * @param studentNumber
//     * @return
//     */
//    @RequestMapping(value = "/findCardInfo",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> findCardInfo(@RequestParam("studentNumber") String studentNumber){
//        Map<String,Object> map=new HashMap<String,Object>();
//        CardCharge cardCharge=cardChargeDaoMapper.getcardinfo(studentNumber);
//        if(cardCharge!=null){
//            map.put("state",true);
//            map.put("result",cardCharge);
//        }else{
//            map.put("state",false);
//            map.put("result","用户信息获取失败");
//        }
//        return map;
//    }
//
//    /**
//     * 根据学生ID获取学生图书借阅信息
//     * @param studentNumber
//     * @return
//     */
//    @RequestMapping(value = "/findBook_borrowInfo",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> findStudentBookInfo(@RequestParam("studentNumber") String studentNumber){
//        Map<String,Object> map=new HashMap<String,Object>();
//        List<Book> books=bookDaoMapper.findAllUserBookInfobyaccount(studentNumber);
//        if(books!=null && books.size()>0){
//            map.put("state",true);
//            map.put("result",books);
//        }else{
//            map.put("state",false);
//            map.put("result","用户图书信息获取失败");
//        }
//        return map;
//    }
//
//    /**
//     * 根据学生ID和书本名字给书本星星评分
//     * @param studentNumber
//     * @param book_name
//     * @param stu_score
//     * @return
//     */
//    @RequestMapping(value = "/giveBookStar",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> giveBookSource(@RequestParam("studentNumber") String studentNumber,@RequestParam("book_name") String book_name,@RequestParam("stu_score") double stu_score){
//        Map<String,Object> map=new HashMap<String,Object>();
//        int total = bookDaoMapper.getStuBookComment_Count(studentNumber,book_name);
//        if(total>0){
//            map.put("state",false);
//            map.put("msg","你已评价过，无法再评价");
//            return map;
//        }
//        else {
//            bookDaoMapper.setStu_source(studentNumber,book_name,stu_score);
//            map.put("state",true);
//            map.put("msg","评价成功");
//            return map;
//        }
//    }
//    /**
//     * 根据书本名字得到综合评价分数和评论
//     * @param book_name
//     * @return
//     */
//    @RequestMapping(value = "/getBookScoreAndComments",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> getBookScoreAndComments(@RequestParam("book_name") String book_name){
//        Map<String,Object> map=new HashMap<String,Object>();
//        double Score;
//        if(bookDaoMapper.getBookScore(book_name)==null){
//             Score = 0;
//        }
//        else{
//             Score = bookDaoMapper.getBookScore(book_name).doubleValue();
//        }
//        List<bookCommentary> Commentaries=bookDaoMapper.getBookComments(book_name);
//        if(Commentaries!=null){
//            map.put("state",true);
//            map.put("comment",Commentaries);
//            map.put("score",Score);
//            return map;
//        }
//        else {
//            map.put("state",false);
//            map.put("msg","获取失败");
//            return map;
//        }
//    }
//
//    /**
//     * 学生给书本评价
//     * @param book_name
//     * @return
//     */
//    @RequestMapping(value = "/giveComments",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> giveComments(@RequestParam("book_name") String book_name,@RequestParam("studentNumber") String studentNumber,@RequestParam("comment") String comment){
//        Map<String,Object> map=new HashMap<String,Object>();
//        if(comment.length()>0){
//            bookDaoMapper.givComments(book_name,studentNumber,comment);
//            map.put("state",true);
//            return map;
//        }
//        else{
//            map.put("state",false);
//            return map;
//        }
//    }
//
//    /**
//     * 校园卡充值数据接口
//     * @param studentaccount
//     * @param charge_money
//     * @return
//     */
//    @RequestMapping(value = "/cardFreeCharage",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> cardFreeCharage(@RequestParam("studentaccount") String studentaccount,
//                                              @RequestParam("chargemonet") String charge_money){
//        CardCharge cardCharge1=cardChargeDaoMapper.getcardinfo(studentaccount);
//        double current_balance=cardCharge1.getCard_balance()+Double.valueOf(charge_money);
//        CardCharge cardCharge=new CardCharge();
//        cardCharge.setCard_balance(current_balance);
//        cardCharge.setChargetime(new Date());
//        cardCharge.setStu_account(studentaccount);
//        cardChargeDaoMapper.chargeFree(cardCharge);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code",true);
//        map.put("result","校园卡充值成功");
//        return map;
//    }
//
//    /**
//     * 电费卡充值数据接口
//     * @param element_number
//     * @param chargemoney
//     * @return
//     */
//    @RequestMapping(value = "/ElementCharage",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> ElementCharage(@RequestParam("element_number") String element_number,
//                                              @RequestParam("chargemoney") String chargemoney){
//        double charge_moeny=Double.valueOf(chargemoney);
//        Element element1=elementDaoMapper.getElemntbynumber(element_number);
//        double current_emeent=element1.getCurrentelement()+(charge_moeny*1.75);
//        Element element=new Element();
//        element.setCurrentelement(current_emeent);
//        element.setElementNumber(element_number);
//        elementDaoMapper.updateElemnt(element);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code",true);
//        map.put("result","寝室电费充值成功");
//        return map;
//    }
}
