<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书馆目录" prop="libraryCatalog">
        <el-input
          v-model="queryParams.libraryCatalog"
          placeholder="请输入图书馆目录"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="期数 " prop="issue">
        <el-input
          v-model="queryParams.issue"
          placeholder="请输入期数 "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ISSN" prop="issn">
        <el-input
          v-model="queryParams.issn"
          placeholder="请输入ISSN"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="DOI" prop="doi">
        <el-input
          v-model="queryParams.doi"
          placeholder="请输入DOI"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词" prop="key">
        <el-input
          v-model="queryParams.key"
          placeholder="请输入关键词"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存档位置" prop="archiveLocation">
        <el-input
          v-model="queryParams.archiveLocation"
          placeholder="请输入存档位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="额外信息" prop="extra">
        <el-input
          v-model="queryParams.extra"
          placeholder="请输入额外信息"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版权" prop="rights">
        <el-input
          v-model="queryParams.rights"
          placeholder="请输入版权"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收藏" prop="collections">
        <el-input
          v-model="queryParams.collections"
          placeholder="请输入收藏"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问日期" prop="accessDate">
        <el-date-picker clearable size="small"
                        v-model="queryParams.accessDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择访问日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="页码" prop="pages">
        <el-input
          v-model="queryParams.pages"
          placeholder="请输入页码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="期刊缩写" prop="journalAbbreviation">
        <el-input
          v-model="queryParams.journalAbbreviation"
          placeholder="请输入期刊缩写"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简短标题" prop="seriestitle">
        <el-input
          v-model="queryParams.seriestitle"
          placeholder="请输入简短标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版物标题" prop="publicationTitle">
        <el-input
          v-model="queryParams.publicationTitle"
          placeholder="请输入出版物标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入版本"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问日期" prop="dateAdded">
        <el-date-picker clearable size="small"
                        v-model="queryParams.dateAdded"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择访问日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改日期 " prop="dateModified">
        <el-date-picker clearable size="small"
                        v-model="queryParams.dateModified"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择修改日期 ">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="简短标题" prop="shorttitle">
        <el-input
          v-model="queryParams.shorttitle"
          placeholder="请输入简短标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="语言" prop="language">
        <el-input
          v-model="queryParams.language"
          placeholder="请输入语言"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者 " prop="creators">
        <el-input
          v-model="queryParams.creators"
          placeholder="请输入创建者 "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列文字" prop="seriesText">
        <el-input
          v-model="queryParams.seriesText"
          placeholder="请输入系列文字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发表日期" prop="date">
        <el-date-picker clearable size="small"
                        v-model="queryParams.date"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择发表日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="标签" prop="tags">
        <el-input
          v-model="queryParams.tags"
          placeholder="请输入标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卷" prop="volume">
        <el-input
          v-model="queryParams.volume"
          placeholder="请输入卷"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="索书号" prop="callNumber">
        <el-input
          v-model="queryParams.callNumber"
          placeholder="请输入索书号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列" prop="series">
        <el-input
          v-model="queryParams.series"
          placeholder="请输入系列"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关系" prop="relations">
        <el-input
          v-model="queryParams.relations"
          placeholder="请输入关系"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:bib:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:bib:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:bib:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="syncBib"
          v-hasPermi="['system:bib:add']"
        >文献同步</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:bib:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bibList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文献id" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" :show-overflow-tooltip="true" />
      <el-table-column label="内容" align="center" prop="content" :show-overflow-tooltip="true" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="摘要 " align="center" prop="abstractNote" :show-overflow-tooltip="true" />
      <el-table-column label="图书馆目录" align="center" prop="libraryCatalog" />
      <el-table-column label="期数 " align="center" prop="issue" />
      <el-table-column label="ISSN" align="center" prop="issn" />
      <el-table-column label="DOI" align="center" prop="doi" />
      <el-table-column label="关键词" align="center" prop="key" />
      <el-table-column label="存档位置" align="center" prop="archiveLocation" />
      <el-table-column label="额外信息" align="center" prop="extra" />
      <el-table-column label="版权" align="center" prop="rights" />
      <el-table-column label="收藏" align="center" prop="collections" />
      <el-table-column label="访问日期" align="center" prop="accessDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accessDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="页码" align="center" prop="pages" />
      <el-table-column label="期刊缩写" align="center" prop="journalAbbreviation" />
      <el-table-column label="简短标题" align="center" prop="seriestitle" />
      <el-table-column label="出版物标题" align="center" prop="publicationTitle" />
      <el-table-column label="版本" align="center" prop="version" />
      <el-table-column label="链接" align="center" prop="url" :show-overflow-tooltip="true" />
      <el-table-column label="访问日期" align="center" prop="dateAdded" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateAdded, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期 " align="center" prop="dateModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="简短标题" align="center" prop="shorttitle" />
      <el-table-column label="语言" align="center" prop="language" />
      <el-table-column label="创建者 " align="center" prop="creators" :show-overflow-tooltip="true" />
      <el-table-column label="系列文字" align="center" prop="seriesText" />
      <el-table-column label="文章类型" align="center" prop="itemType" />
      <el-table-column label="发表日期" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="标签" align="center" prop="tags" :show-overflow-tooltip="true" />
      <el-table-column label="卷" align="center" prop="volume" />
      <el-table-column label="索书号" align="center" prop="callNumber" />
      <el-table-column label="系列" align="center" prop="series" />
      <el-table-column label="关系" align="center" prop="relations" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:bib:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:bib:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改文献管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="摘要 " prop="abstractNote">
          <el-input v-model="form.abstractNote" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="图书馆目录" prop="libraryCatalog">
          <el-input v-model="form.libraryCatalog" placeholder="请输入图书馆目录" />
        </el-form-item>
        <el-form-item label="期数 " prop="issue">
          <el-input v-model="form.issue" placeholder="请输入期数 " />
        </el-form-item>
        <el-form-item label="ISSN" prop="issn">
          <el-input v-model="form.issn" placeholder="请输入ISSN" />
        </el-form-item>
        <el-form-item label="DOI" prop="doi">
          <el-input v-model="form.doi" placeholder="请输入DOI" />
        </el-form-item>
        <el-form-item label="关键词" prop="key">
          <el-input v-model="form.key" placeholder="请输入关键词" />
        </el-form-item>
        <el-form-item label="存档位置" prop="archiveLocation">
          <el-input v-model="form.archiveLocation" placeholder="请输入存档位置" />
        </el-form-item>
        <el-form-item label="额外信息" prop="extra">
          <el-input v-model="form.extra" placeholder="请输入额外信息" />
        </el-form-item>
        <el-form-item label="版权" prop="rights">
          <el-input v-model="form.rights" placeholder="请输入版权" />
        </el-form-item>
        <el-form-item label="收藏" prop="collections">
          <el-input v-model="form.collections" placeholder="请输入收藏" />
        </el-form-item>
        <el-form-item label="访问日期" prop="accessDate">
          <el-date-picker clearable size="small"
                          v-model="form.accessDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择访问日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="页码" prop="pages">
          <el-input v-model="form.pages" placeholder="请输入页码" />
        </el-form-item>
        <el-form-item label="期刊缩写" prop="journalAbbreviation">
          <el-input v-model="form.journalAbbreviation" placeholder="请输入期刊缩写" />
        </el-form-item>
        <el-form-item label="简短标题" prop="seriestitle">
          <el-input v-model="form.seriestitle" placeholder="请输入简短标题" />
        </el-form-item>
        <el-form-item label="出版物标题" prop="publicationTitle">
          <el-input v-model="form.publicationTitle" placeholder="请输入出版物标题" />
        </el-form-item>
        <el-form-item label="版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入版本" />
        </el-form-item>
        <el-form-item label="链接" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="访问日期" prop="dateAdded">
          <el-date-picker clearable size="small"
                          v-model="form.dateAdded"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择访问日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改日期 " prop="dateModified">
          <el-date-picker clearable size="small"
                          v-model="form.dateModified"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择修改日期 ">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="简短标题" prop="shorttitle">
          <el-input v-model="form.shorttitle" placeholder="请输入简短标题" />
        </el-form-item>
        <el-form-item label="语言" prop="language">
          <el-input v-model="form.language" placeholder="请输入语言" />
        </el-form-item>
        <el-form-item label="创建者 " prop="creators">
          <el-input v-model="form.creators" placeholder="请输入创建者 " />
        </el-form-item>
        <el-form-item label="系列文字" prop="seriesText">
          <el-input v-model="form.seriesText" placeholder="请输入系列文字" />
        </el-form-item>
        <el-form-item label="发表日期" prop="date">
          <el-date-picker clearable size="small"
                          v-model="form.date"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择发表日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-input v-model="form.tags" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="卷" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入卷" />
        </el-form-item>
        <el-form-item label="索书号" prop="callNumber">
          <el-input v-model="form.callNumber" placeholder="请输入索书号" />
        </el-form-item>
        <el-form-item label="系列" prop="series">
          <el-input v-model="form.series" placeholder="请输入系列" />
        </el-form-item>
        <el-form-item label="关系" prop="relations">
          <el-input v-model="form.relations" placeholder="请输入关系" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBib, getBib, delBib, addBib, updateBib,syncBib } from "@/api/system/bib";

export default {
  name: "Bib",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 文献管理表格数据
      bibList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        author: null,
        abstractNote: null,
        libraryCatalog: null,
        issue: null,
        issn: null,
        doi: null,
        key: null,
        archiveLocation: null,
        extra: null,
        rights: null,
        collections: null,
        accessDate: null,
        pages: null,
        journalAbbreviation: null,
        seriestitle: null,
        publicationTitle: null,
        version: null,
        url: null,
        dateAdded: null,
        dateModified: null,
        shorttitle: null,
        language: null,
        creators: null,
        seriesText: null,
        itemType: null,
        date: null,
        tags: null,
        volume: null,
        callNumber: null,
        series: null,
        relations: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文献管理列表 */
    getList() {
      this.loading = true;
      listBib(this.queryParams).then(response => {
        this.bibList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        content: null,
        author: null,
        abstractNote: null,
        libraryCatalog: null,
        issue: null,
        issn: null,
        doi: null,
        key: null,
        archiveLocation: null,
        extra: null,
        rights: null,
        collections: null,
        accessDate: null,
        pages: null,
        journalAbbreviation: null,
        seriestitle: null,
        publicationTitle: null,
        version: null,
        url: null,
        dateAdded: null,
        dateModified: null,
        shorttitle: null,
        language: null,
        creators: null,
        seriesText: null,
        itemType: null,
        date: null,
        tags: null,
        volume: null,
        callNumber: null,
        series: null,
        relations: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文献管理";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBib(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文献管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBib(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBib(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除文献管理编号为"' + ids + '"的数据项？').then(function() {
        return delBib(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/bib/export', {
        ...this.queryParams
      }, `bib_${new Date().getTime()}.xlsx`)
    },
    /** 同步按钮操作 **/
    handleSync() {
      this.$modal.confirm('是否确认同步文献管理数据？').then(function() {
        return syncBib();
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("同步成功");
      }).catch(() => {});
    }
  }
};
</script>
