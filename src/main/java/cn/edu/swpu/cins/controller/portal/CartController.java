package cn.edu.swpu.cins.controller.portal;

import cn.edu.swpu.cins.dto.http.Const;
import cn.edu.swpu.cins.dto.http.HttpResult;
import cn.edu.swpu.cins.dto.view.CartVo;
import cn.edu.swpu.cins.entity.User;
import cn.edu.swpu.cins.enums.HttpResultEnum;
import cn.edu.swpu.cins.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @RequestMapping(value = "/addProductToCart", method = RequestMethod.POST)
    public HttpResult<CartVo> addProductToCart(HttpSession session, Integer count, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return HttpResult.createByErrorCodeMessage(HttpResultEnum.NEED_LOGIN.getCode(), HttpResultEnum.NEED_LOGIN.getDescrption());
        }
        return cartService.add(user.getId(), productId, count);
    }
}
