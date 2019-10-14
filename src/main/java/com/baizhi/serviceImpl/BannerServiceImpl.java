package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public List<Banner> findAll(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        List<Banner> byPage = bannerMapper.findAll(start, rows);
        return byPage;
    }

    @Override
    public Integer count() {
        Integer count = bannerMapper.count();
        return count;
    }

    @Override
    public void save(Banner banner) {
        bannerMapper.save(banner);
    }

    @Override
    public Banner findOne(String id) {
        Banner one = bannerMapper.findOne(id);
        return one;
    }

    @Override
    public void delete(Banner banner) {
        bannerMapper.delete(banner);
    }

    @Override
    public void updateImg(Banner banner) {
        bannerMapper.updateImg(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.update(banner);
    }

    @Override
    public List<Banner> select() {
        List<Banner> select = bannerMapper.select();
        for (Banner banner : select) {
            banner.setImg_path("D:\\后期项目\\source\\cmfz\\src\\main\\webapp\\img\\" + banner.getImg_path());
        }


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图详细信息", "轮播图"),
                Banner.class, select);
        try {
            workbook.write(new FileOutputStream(new File("G:\\导出/easy.xls")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导出成功");
        return select;
    }


}
