package utils;

import bo.ArticleVenduBO;
import dal.ArticleVenduDAO;

import java.util.List;

public abstract class Utils {
    public static List<ArticleVenduBO> ajouteSiExistePas(List<ArticleVenduBO> res, List<ArticleVenduBO> ajout){
        boolean notIn = true;
        for (ArticleVenduBO avAdd:ajout
             ) {

            for (ArticleVenduBO avIn:res
                 ) {
                if (avAdd.getId() == avIn.getId())
                    notIn = false;
            }
            if (notIn)
                res.add(avAdd);
        }
        return res;
    }
}
