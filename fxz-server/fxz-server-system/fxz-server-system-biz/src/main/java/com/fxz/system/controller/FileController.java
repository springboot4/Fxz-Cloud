package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.log.annotation.OperLogAnn;
import com.fxz.common.log.enums.BusinessType;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.system.dto.FileDto;
import com.fxz.system.entity.File;
import com.fxz.system.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件管理
 *
 * @author fxz
 * @date 2022-04-04
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;

	/**
	 * 上传文件
	 */
	@OperLogAnn(title = "文件管理", businessType = BusinessType.INSERT)
	@PostMapping(value = "/add")
	public Result<Object> add(@RequestPart("file") MultipartFile file) {
		return Result.success(fileService.addFile(file));
	}

	/**
	 * 下载文件
	 * @param bucket 桶名称
	 * @param fileName 文件名
	 * @param response 响应
	 */
	@Ojbk
	@GetMapping("/{bucket}/{fileName}")
	public void file(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
		fileService.getFile(bucket, fileName, response);
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody FileDto fileDto) {
		return Result.success(fileService.updateFile(fileDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(fileService.deleteFile(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<File> findById(Long id) {
		return Result.success(fileService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<File>> findAll() {
		return Result.success(fileService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<File>> pageFile(Page<File> pageParam, File file) {
		return Result.success(PageResult.success(fileService.pageFile(pageParam, file)));
	}

}